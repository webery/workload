/*
 * weber 于 2014.12.18修改，增加了图片校验和错误信息显示功能
 *
 *使用方法:
 *界面构造(IMG标签外必须拥有DIV 而且必须给予DIV控件ID)
 * <div id="imgdiv"><img id="imgShow" width="120" height="120" /></div>
 * <input type="file" id="up_img" />
 *调用代码:
 * new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
 *参数说明:
 *UpBtn:选择文件控件ID;
 *DivShow:DIV控件ID;(可选)
 *ImgShow:图片控件ID;
 *Width:预览宽度;
 *Height:预览高度;
 *ImgType:支持文件类型 格式:'gif,jpeg,jpg,bmp,png'字符串  文件类型之间用,分隔
 *MinSize:图片大小
 *MaxSize:图片大小
 *ErrMsgShow 显示错误信息的ID,
 *callback:选择文件后回调方法;
 */

/*
 *work:图片预览插件
 */
var uploadPreview = function(setting) {
	/*
	 * work:this(当前对象)
	 */
	var _self = this;
	/*
	 * work:判断为null或者空值
	 */
	_self.IsNull = function(value) {
		if (value == undefined || value == null || value == ""
				|| value.length == 0) {
			return true;
		}
		return false;
	}
	/*
	 * work:默认配置
	 */
	_self.DefautlSetting = {
		UpBtn : "",
		DivShow : "",
		ImgShow : "",
		Width : 100,
		Height : 100,
		DefaultImgUrl : '',//'resources/images/geshou.jpg',
		ImgType : 'gif,jpeg,jpg,bmp,png',// [ "gif", "jpeg", "jpg", "bmp",
		// "png" ],
		MinSize : '10',
		MaxSize : '200',
		ErrMsg : "选择文件错误,图片类型必须是(gif,jpeg,jpg,bmp,png)中的一种",
		ErrMsgShow : '',// 显示错误信息的ID,
		callback : function() {
		}
	};
	
	/*
	 * work:读取配置
	 */
	_self.Setting = {
		UpBtn : _self.IsNull(setting.UpBtn) ? _self.DefautlSetting.UpBtn
				: setting.UpBtn,
		DivShow : _self.IsNull(setting.DivShow) ? _self.DefautlSetting.DivShow
				: setting.DivShow,
		ImgShow : _self.IsNull(setting.ImgShow) ? _self.DefautlSetting.ImgShow
				: setting.ImgShow,
		Width : _self.IsNull(setting.Width) ? _self.DefautlSetting.Width
				: setting.Width,
		Height : _self.IsNull(setting.Height) ? _self.DefautlSetting.Height
				: setting.Height,
		DefaultImgUrl : _self.IsNull(setting.DefaultImgUrl) ? _self.DefautlSetting.DefaultImgUrl
				: setting.DefaultImgUrl,
		ImgType : _self.IsNull(setting.ImgType) ? _self.DefautlSetting.ImgType
				: setting.ImgType,
		ErrMsg : _self.IsNull(setting.ErrMsg) ? _self.DefautlSetting.ErrMsg
				: setting.ErrMsg,
		ErrMsgShow : _self.IsNull(setting.ErrMsgShow) ? _self.DefautlSetting.ErrMsgShow
				: setting.ErrMsgShow,
		MinSize : _self.IsNull(setting.MinSize) ? _self.DefautlSetting.MinSize
				: setting.MinSize,
		MaxSize : _self.IsNull(setting.MaxSize) ? _self.DefautlSetting.MaxSize
				: setting.MaxSize,
		//callback : _self.IsNull(setting.callback) ? _self.DefautlSetting.callback
				//: setting.callback
		callback : typeof(setting.callback) == 'undefined' ? _self.DefautlSetting.callback
				 : setting.callback,
	};

	/*
	 * work:获取文本控件URL
	 */
	_self.getObjectURL = function(file) {
		var url = null;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
	/*
	 * work:绑定事件
	 */
	_self.Bind = function() {
		document.getElementById(_self.Setting.UpBtn).onchange = function() {
			if (this.value) {
				if (!_self.imageValid(this)) {
					// alert(_self.Setting.ErrMsg);
					// _self.showErrorMeg
					this.value = "";
					_self.showErrorMeg(true);// 显示错误信息
					// 显示默认的预览图片
					document.getElementById(_self.Setting.ImgShow).src = _self.Setting.DefaultImgUrl;
					return false;
				}
				if (navigator.userAgent.indexOf("MSIE") > -1) {
					try {
						_self.showErrorMeg(false);
						document.getElementById(_self.Setting.ImgShow).src = _self
								.getObjectURL(this.files[0]);
					} catch (e) {
						var div = document
								.getElementById(_self.Setting.DivShow);
						this.select();
						top.parent.document.body.focus();
						var src = document.selection.createRange().text;
						document.selection.empty();
						document.getElementById(_self.Setting.ImgShow).style.display = "none";
						div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
						div.style.width = _self.Setting.Width + "px";
						div.style.height = _self.Setting.Height + "px";
						div.filters
								.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
					}
				} else {
					_self.showErrorMeg(false);
					document.getElementById(_self.Setting.ImgShow).src = _self
							.getObjectURL(this.files[0]);
				}
				_self.Setting.callback();
			}
		}
	}
	/**
	 * 文件校验 weber 2014.12.18
	 */
	_self.imageValid = function(file) {
		
		try {
			var filePath = file.value.toLowerCase();
			var size = file.files[0].size / 1024;

			var fileSuffix = filePath.substring(filePath.lastIndexOf(".") + 1,
					filePath.length).toLowerCase();
			// 根据文件后缀校验文件类型
			var regExp = new RegExp(fileSuffix);
			if (!regExp.test(_self.Setting.ImgType)) {
				return false;
			}

			if (size > _self.Setting.MaxSize || size < _self.Setting.MinSize) {
				return false;
			}
		} catch (exception) {
			return false;
		}

		return true;

	}

	/**
	 * 显示错误信息 weber 2014.12.18
	 */
	_self.showErrorMeg = function(flag) {
		
		var target = null;
		target = document.getElementById(_self.Setting.ErrMsgShow);
		
		if(target == null) {
			return ;
		}
		
		if (flag) {
			target.innerHTML = _self.Setting.ErrMsg;
		} else {
			target.innerHTML = '';
		}

	}

	/*
	 * work:执行绑定事件
	 */
	_self.Bind();
}
