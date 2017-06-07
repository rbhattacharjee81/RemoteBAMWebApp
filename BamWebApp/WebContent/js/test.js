$(document).ready(
		function() {
			$.jqplot('pieChart', [ [ [ 'XX01', 7.28 ], [ 'XX02', 3.28 ],
					[ 'XX03', 3.31 ], [ 'XX04', 2.42 ], [ 'XX05', 2.4 ],
					[ 'XX06', 1.61 ], [ 'XX07', 2.45 ], [ 'XX08', 1.69 ],
					[ 'XX09', 2.18 ], [ 'XX10', 1.6 ], [ 'XX11', 1.78 ] ] ], {
				seriesDefaults : {
					renderer : jQuery.jqplot.PieRenderer,
					rendererOptions : {
						showDataLabels : true,
						dataLabels : 'value',
						dataLabelFormatString : '%.2f%%',
						dataLabelPositionFactor : 1.2,
						shadow : false,
						sliceMargin : 3
					},
				}
			});
		});