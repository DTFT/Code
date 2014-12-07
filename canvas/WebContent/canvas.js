var canvas = document.getElementById('canvas');
var cxt = canvas.getContext('2d');

var Brush = document.getElementById('means_brush');
var Eraser = document.getElementById('means_eraser');
var Paint = document.getElementById('means_paint');
var Xiguan = document.getElementById('means_xiguan');
var Text = document.getElementById('means_text');
var Fangdajing = document.getElementById('means_fangdajing');

var Line = document.getElementById('shape_line');
var Circle = document.getElementById('shape_circle');
var Square = document.getElementById('shape_square');
var Poly = document.getElementById('shape_poly');
var Fill_circle = document.getElementById('shape_fill_circle');
var Fill_square = document.getElementById('shape_fill_square');

// ������״��ǩ����һ������
var actions = [ Brush, Eraser, Paint, Xiguan, Text, Fangdajing, Line, Circle,
		Square, Poly, Fill_circle, Fill_square ];

// ��ȡ�߿�ť
var Line_1 = document.getElementById('width_1');
var Line_3 = document.getElementById('width_3');
var Line_5 = document.getElementById('width_5');
var Line_8 = document.getElementById('width_8');
// ���߿��������
var width = [ Line_1, Line_3, Line_5, Line_8 ];

// ��ȡ��ɫ��ť
var ColorRed = document.getElementById('red');
var ColorGreen = document.getElementById('green');
var ColorBlue = document.getElementById('blue');
var ColorYellow = document.getElementById('yellow');
var ColorWhite = document.getElementById('white');
var ColorBlack = document.getElementById('black');
var ColorPink = document.getElementById('pink');
var ColorPurple = document.getElementById('purple');
var ColorCyan = document.getElementById('cyan');
var ColorOrange = document.getElementById('orange');

// ����ɫ��������
var colors = [ ColorRed, ColorGreen, ColorBlue, ColorYellow, ColorWhite,
		ColorBlack, ColorPink, ColorPurple, ColorCyan, ColorOrange, ];

function setStatus(Arr, num, type) {
	for (var i = 0; i < Arr.length; i++) {
		// ����ѡ�е�Ԫ�ر�css
		if (i == num) {
			// ���øı䱳��ɫ���Ǳ߿�
			if (type == 1) {
				Arr[i].style.background = "yellow";
			} else {
				Arr[i].style.border = "1px solid #fff";
			}
		} else {
			// ����δѡ�е�������ǩ
			if (type == 1) {
				Arr[i].style.background = "#ccc";
			} else {
				Arr[i].style.border = "1px solid #000";
			}
		}
	}
}

// 设置初始值，默认画笔工具
drawBrush(0);
// 默认设置颜色
setLineWidth(0);
// 默认设置线宽
setColor(ColorRed, 0);

//设置功能函数
function saveimg(){
	 var imgdata=canvas.toDataURL();
	 //去掉前22字符
	 var b64=imgdata.substring(22);
//	 alert(imgdata);
	 //将form表单中的隐藏表单  赋值（获取的b64）
	 var data=document.getElementById('data');
	 data.value=b64;
	 //将表单提交到后台去 //http://localhost/down.php
	 var form=document.getElementById('myform');
	 form.submit();
}

function clearimg(){
	//画布清除方法
	cxt.clearRect(0,0,880,400);
}

// 工具

// 画笔
function drawBrush(num) {

	setStatus(actions, num, 1);
	var flag = 0;// 设置标志位，检测鼠标是否摁下
	canvas.onmousedown = function(evt) {

		evt = window.event || evt;

		var startX = evt.pageX - this.offsetLeft;
		var startY = evt.pageY - this.offsetTop;
		cxt.beginPath();
		cxt.moveTo(startX, startY);
		flag = 1;
	}

	// 鼠标移动,获取鼠标位置
	canvas.onmousemove = function(evt) {
		evt = window.event || evt;
		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;
		// 判断一下鼠标是否摁下
		if (flag) {
			// 移动的时候设置路径并画出
			cxt.lineTo(endX, endY);
			cxt.stroke();
		}
	}

	// 鼠标抬起，结束
	canvas.onmouseup = function() {
		flag = 0;
	}

	// 鼠标移除取消画图
	canvas.onmouseout = function() {
		flag = 0;
	}
}

// 橡皮功能
var eraserFlag = 0;// 设置橡皮标志位为0
function drawEraser(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		evt = window.event || evt;

		var eraserX = evt.pageX - this.offsetLeft;
		var eraserY = evt.pageY - this.offsetTop;

		// canvas擦除方法，cxt.clearRect()
		cxt.clearRect(eraserX - cxt.lineWidth, eraserY - cxt.lineWidth,
				cxt.lineWidth * 2, cxt.lineWidth * 2);
		eraserFlag = 1;
	}
	// 随着鼠标移动不停的擦除
	canvas.onmousemove = function(evt) {
		evt = window.event || evt;
		var eraserX = evt.pageX - this.offsetLeft;
		var eraserY = evt.pageY - this.offsetTop;
		// canvas擦除方法，cxt.clearRect()
		// 判断鼠标点击
		if (eraserFlag == 1) {

			cxt.clearRect(eraserX - cxt.lineWidth, eraserY - cxt.lineWidth,
					cxt.lineWidth * 2, cxt.lineWidth * 2);
		}

	}
	// 鼠标抬起，标志位置为0
	canvas.onmouseup = function() {
		eraserFlag = 0;
	}
	canvas.onmouseout = function() {
		eraserFlag = 0;
	}
}

// 油漆桶功能
function drawPaint(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function() {
		// 把画布涂成制定颜色
		cxt.fillRect(0, 0, 880, 400);
		// 颜色在setColor的fillcolor中已设置
	}
	canvas.onmouseup = null;
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// 吸管
function drawXiguan(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		evt = window.event || evt;
		var startX = evt.pageX - this.offsetLeft;
		var startY = evt.pageY - this.offsetTop;
		// 获取该点坐标的颜色信息
		// 获取图像信息的方法getImageData,是一个对象
		var obj = cxt.getImageData(startX, startY, 1, 1); // 一个像素点
		// obj.data=[红，绿，蓝，透明度] 1像素数据
		// obj.data=[红，绿，蓝，透明度，红，绿，蓝，透明度，....] 多像素数据信息
		// 吸出颜色
		var color = 'rgb(' + obj.data[0] + ',' + obj.data[1] + ','
				+ obj.data[2] + ')';
		// 将颜色设定到应用中
		cxt.strokeStyle = color;
		cxt.fillStyle = color;
		// 调用画笔工具
		drawBrush(0);
	}
	// 取消其他事件
	canvas.onmouseup = null;
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// 文字
function drawText(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		evt = window.event || evt;
		var startX = evt.pageX - this.offsetLeft;
		var startY = evt.pageY - this.offsetTop;

		// 获取输入信息window.prompt(对话框提示,默认值);
		var userVal = window.prompt('请在这里输入文字', '');
		// alert(userVal);
		// 将文字写到画布上
		if (userVal != null) {
			cxt.fillText(userVal, startX, startY);
		}
	}
	canvas.onmouseup = null;
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// 放大镜
function drawFangdajing(num) {
	setStatus(actions, num, 1);
	// 用户输入数据
	var scale = window.prompt('请输入要放大的百分比[整型]', '100');
	// 把数据转化对应的canvas画布大小
	var scaleW = 880 * scale / 100;
	var scaleH = 400 * scale / 100;
	// 将数据设置到对应html标签上
	canvas.style.width = parseInt(scaleW) + 'px';
	canvas.style.height = parseInt(scaleH) + 'px';
}

// 形状
// 划线
function drawLine(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		evt = window.event || evt;
		// 获取起始点坐标，相对canvas画布
		var startX = evt.pageX - this.offsetLeft;
		var startY = evt.pageY - this.offsetTop;
		// 设置直线的开始点
		cxt.beginPath();// 重置当前路径
		cxt.moveTo(startX, startY);
	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
	// 鼠标抬起
	canvas.onmouseup = function(evt) {
		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;
		// 设置路径，连接开始点和结束点，然后绘图
		cxt.lineTo(endX, endY);
		cxt.closePath();
		cxt.stroke();
	}
}
// 将变量设置为全局变量
var arcX = 0;
var arcY = 0;
// 画圆圈
function drawCircle(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		// 获取圆心位置
		evt = window.event || evt;
		arcX = evt.pageX - this.offsetLeft;
		arcY = evt.pageY - this.offsetTop;
	}
	canvas.onmouseup = function(evt) {
		evt = window.event || evt;
		// 获取半径
		// 实际获取的是一个坐标
		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;
		// 计算C(半径)的距离
		var a = endX - arcX;
		var b = endY - arcY;
		var c = Math.sqrt(a * a + b * b);
		// 画图
		cxt.beginPath();
		cxt.arc(arcX, arcY, c, 0, 360, false);
		cxt.closePath();
		cxt.stroke();
	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// 方框
var rectX = 0;
var rectY = 0;
function drawSquare(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {

		evt = window.event || evt;
		rectX = evt.pageX - this.offsetLeft;
		rectY = evt.pageY - this.offsetTop;
	}
	canvas.onmouseup = function(evt) {
		evt = window.event || evt;

		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;

		var rectW = endX - rectX;
		var rectH = endY - rectY;

		// 画图
		// 不需要开始路径技术路径
		cxt.strokeRect(rectX, rectY, rectW, rectH);

	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;

}

// 三角形
var polyX = 0;
var polyY = 0;
function drawPoly(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		// 获取圆心位置
		evt = window.event || evt;
		polyX = evt.pageX - this.offsetLeft;
		polyY = evt.pageY - this.offsetTop;
	}

	canvas.onmouseup = function(evt) {
		evt = window.event || evt;

		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;

		cxt.beginPath();// 重置当前路径
		// 将画笔移动到右下角的点
		cxt.moveTo(endX, endY);
		// 计算左下角点
		var lbX = 2 * polyX - endX;
		var lbY = endY;
		cxt.lineTo(lbX, lbY);
		// 设置定点坐标
		var tempC = 2 * (endX - polyX);
		var tempA = endX - polyX;
		var tempB = Math.sqrt(tempC * tempC - tempA * tempA);
		// 定点坐标polyX,endY-tempB

		// 画图
		cxt.lineTo(polyX, endY - tempB);
		cxt.closePath();
		cxt.stroke();

	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// ʵ��Բ
function drawFill_circle(num) {
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {
		// 获取圆心位置
		evt = window.event || evt;
		arcX = evt.pageX - this.offsetLeft;
		arcY = evt.pageY - this.offsetTop;
	}
	canvas.onmouseup = function(evt) {
		evt = window.event || evt;
		// 获取半径
		// 实际获取的是一个坐标
		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;
		// 计算C(半径)的距离
		var a = endX - arcX;
		var b = endY - arcY;
		var c = Math.sqrt(a * a + b * b);
		// 画图
		cxt.beginPath();
		cxt.arc(arcX, arcY, c, 0, 360, false);
		cxt.closePath();
		cxt.fill();
	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// ʵ�ľ���
function drawFill_square(num) {
	setStatus(actions, num, 1);
	setStatus(actions, num, 1);
	canvas.onmousedown = function(evt) {

		evt = window.event || evt;
		rectX = evt.pageX - this.offsetLeft;
		rectY = evt.pageY - this.offsetTop;
	}
	canvas.onmouseup = function(evt) {
		evt = window.event || evt;

		var endX = evt.pageX - this.offsetLeft;
		var endY = evt.pageY - this.offsetTop;

		var rectW = endX - rectX;
		var rectH = endY - rectY;

		// 画图
		// 不需要开始路径技术路径
		cxt.fillRect(rectX, rectY, rectW, rectH);

	}
	canvas.onmousemove = null;// 注销掉其他工具注册的事件
	canvas.onmouseout = null;
}

// �߿���
function setLineWidth(num) {
	setStatus(width, num, 1);
	switch (num) {
	case 0:
		cxt.lineWidth = 1;
		break;
	case 1:
		cxt.lineWidth = 3;
		break;
	case 2:
		cxt.lineWidth = 5;
		break;
	case 3:
		cxt.lineWidth = 8;
		break;
	default:
		cxt.lineWidth = 1;
	}
}

// ��ɫ���ú���
function setColor(obj, num) {
	setStatus(colors, num, 0);
	// 设置画笔颜色和填充颜色
	cxt.strokeStyle = obj.id;
	cxt.fillStyle = obj.id;
}
