package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.StockPurchaseBean;
import com.rays.pro4.Model.StockPurchaseModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "StockPurchaseCtl", urlPatterns = { "/ctl/StockPurchaseCtl" })
public class StockPurchaseCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("purchasePrice"))) {
			request.setAttribute("purchasePrice", PropertyReader.getValue("error.require", "purchasePrice"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "purchaseDate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("orderType"))) {
			request.setAttribute("orderType", PropertyReader.getValue("error.require", "orderType"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		StockPurchaseBean bean = new StockPurchaseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));

		bean.setPurchasePrice(DataUtility.getInt(request.getParameter("purchasePrice")));

		bean.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));

		bean.setOrderType(DataUtility.getString(request.getParameter("orderType")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		StockPurchaseModel model = new StockPurchaseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("product Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			StockPurchaseBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		StockPurchaseModel model = new StockPurchaseModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			StockPurchaseBean bean = (StockPurchaseBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("data is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("data not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("data is successfully Added", request);
					ServletUtility.forward(getView(), request, response);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("data not added");
					e.printStackTrace();
				}

			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.STOCKPURCHASE_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.STOCKPURCHASE_VIEW;
	}

}