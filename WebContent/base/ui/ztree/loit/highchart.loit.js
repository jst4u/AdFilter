Highcharts.loit = {
	global : {
		useUTC : false
	},
	lang : {
		contextButtonTitle : '功能菜单',
		downloadJPEG : '导出图片(JPEG格式)',
		downloadPDF : '导出文档（PDF格式）',
		downloadPNG : '导出图片（PNG格式）',
		downloadSVG : '导出文档（SVG格式）',
		loading : '图形加载中，请稍后......',
		numericSymbols : '',
		printChart : '打印图形',
		resetZoom : '恢复比例',
		resetZoomTitle : '恢复原始比例(1:1)',
		shortMonths : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
				'十一月', '十二月' ],
		weekdays : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ]
	},
	chart : {
		style : {
			fontFamily : '"微软雅黑","宋体","Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif', // default
			fontSize : '24px'
		}
	},
	title : {
		style : {
			font: '24px 微软雅黑',
			lineHeight : 20
		}
	},
	legend: {
		itemStyle: {
			font: '20px 微软雅黑'
		}
	},
	xAxis : {
		labels : {
			style : {
				font: '16px 微软雅黑'
			}
		}
	},
	yAxis : {
		labels : {
			style : {
				font : '14px Arial'
			}
		},
		title : {
			style : {
				font : '16px 微软雅黑'
			}
		}
	}
};

//Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.loit);
