/**
 * 点击图片放大预览
 * weber 2015/1/11
 * 
 */

/*
 * demo
$(function(){
	imagePreViewHandle('#test', false);
})
*/
/**
 * @param selector 选择器，和jq一样
 * @param destroy 设置是否销毁生成的预览标签,true:销毁。false：不销毁
 */
function imagePreViewHandle(selector, destroy) {
	
	var targets = document.querySelectorAll(selector);
	for(var i=0; i<targets.length; i++) {
		var target = targets[i];
	
		target.onclick = function() {
			createImagePreView(this, destroy);
		};
	}
}

function createImagePreView(target, destroy) {

	var imagePreView = document.getElementById('div');
	if(imagePreView != null) {
		imagePreView.style.display = 'block';
		return false;
	}

	var imagePreView = document.createElement('div');
	imagePreView.id = "imagePreView";
	imagePreView.style.cssText = 'position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:9999;'
  		                       + 'width:100%;height:100%;display:block;';
	
	document.body.appendChild(imagePreView);
    
	var windowW = screen.availWidth;//imagePreView.width;
	var windowH = screen.availHeight;//imagePreView.height;

    var imagePreImg = document.createElement('img');
	imagePreImg.style.cssText = 'border:5px solid #fff;';
	imagePreImg.src = target.src;

    var realWidth = imagePreImg.width;//获取图片真实宽度
    var realHeight = imagePreImg.height;//获取图片真实高度
    var imgWidth, imgHeight;
    var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
         
    if(realHeight>windowH*scale) {//判断图片高度
		imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
        imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
        if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
			imgWidth = windowW*scale;//再对宽度进行缩放
        }
    } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
		imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
    } else {//如果图片真实高度和宽度都符合要求，高宽不变
		 imgWidth = realWidth;
		 imgHeight = realHeight;
    }

	var imagePreBox = document.createElement('div');
	imagePreBox.id = 'imagePreBox';
	imagePreBox.style.cssText = 'position:absolute;'
	                          + 'left:calc(50% - ' + imgWidth/2 + 'px);'
							  + 'top:calc(50% - ' + imgHeight/2 + 'px);';

	imagePreImg.style.width = imgWidth + 'px';
	imagePreImg.style.height = imgHeight + 'px';

	imagePreBox.appendChild(imagePreImg);
	imagePreView.appendChild(imagePreBox);

	imagePreView.onclick = function() {
		if(destroy === true) {
			document.getElementById('imagePreView').style.display = 'none';
		}
	    else {
			document.body.removeChild(document.getElementById('imagePreView'));
		}
	};
}

