package com.loit.core.web.tag.highchart;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import com.loit.core.spring.CommonDao;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.StringUtil;
import com.loit.core.web.chart.HighChart;
import com.loit.core.web.chart.Series;
import com.loit.core.web.tag.easyui.BaseHtmlTag;

public class ChartSqlTag extends BaseHtmlTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dataSource;
	private String dimColumn;// 维度列
	private String indexColumns;// 指标[图例名]
	private String chartTypes;// 指标对应的图形类型
	private String bodyString = null;

	public int doStartTag() throws JspException {
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		// 如果chartTypes 有值，则其数量应该同indexColumns相同
		if (null != this.chartTypes && this.chartTypes.split(",").length != this.indexColumns.split(",").length) {
			throw new JspException("如果chartTypes 有值，则其数量应该同indexColumns相同");
		}
		return EVAL_BODY_AGAIN;
	}

	public int doAfterBody() {
		bodyContent = getBodyContent();
		// 取body的内容
		if (bodyContent != null) {
			this.bodyString = bodyContent.getString().trim();
			bodyContent.clearBody();
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		// 1.query data
		List result = null;
		if (!StringUtil.isEmpty(this.bodyString)) {
			CommonDao dao = SpringContext.getBeanOfType(CommonDao.class);
			result = dao.findBySql(this.bodyString, null, null, null);
		} else {
			return super.doEndTag();
		}
		Object chartTag = super.getParent();
		if (chartTag == null || !(chartTag instanceof ChartTag)) {
			throw new JspException("必须是chartTag!");
		}
		HighChart chart = (HighChart) pageContext.getAttribute(((ChartTag) chartTag).getId());

		// 2.construct series ,set chart series

		String[] seriesNames = this.indexColumns.toUpperCase().split(",");
		String[] colNames = new String[seriesNames.length];
		String[] types = null == this.chartTypes ? null : this.chartTypes.split(",");
		for (int j = 0; j < seriesNames.length; j++) {
			Series s = new Series();
			String[] seriesCol = seriesNames[j].split(":");
			if (seriesCol.length > 1) {
				s.setName(seriesCol[1]);
			} else {
				s.setName(seriesCol[0]);
			}
			colNames[j] = seriesCol[0];

			if (null != types) {
				s.setType(types[j]);
			}
			chart.getSeries().add(j, s);
		}

		for (int i = 0; i < result.size(); i++) {
			if (null != this.dimColumn) {
				chart.getxAxis().getCategories().add((String) ((Map) result.get(i)).get(this.dimColumn.toUpperCase()));
			}

			for (int j = 0; j < colNames.length; j++) {
				Object d = ((Map) result.get(i)).get(colNames[j]);
				if (null != d && d instanceof String) {
					d = Double.valueOf(d.toString());
				}
				chart.getSeries().get(j).getData().add(d);
			}
		}

		return super.doEndTag();
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getdimColumn() {
		return dimColumn;
	}

	public void setdimColumn(String dimColumn) {
		this.dimColumn = dimColumn;
	}

	public String getIndexColumns() {
		return indexColumns;
	}

	public void setIndexColumns(String indexColumns) {
		this.indexColumns = indexColumns;
	}

	public String getChartTypes() {
		return chartTypes;
	}

	public void setChartTypes(String chartTypes) {
		this.chartTypes = chartTypes;
	}

	public void release() {
		this.bodyString = null;
		super.release();
	}

}
