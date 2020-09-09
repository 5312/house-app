const baseUrl = "http://ming.ydeshui.com/api/"
export default {
	dateFormat(date,fmat='YYYY-MM'){
		const result=new Date(date)
		const Y=result.getFullYear()
		const M=result.getMonth()+1
		const D=result.getDate()
		const h=result.getHours()
		const m=result.getMinutes()
		const s=result.getSeconds()
		let val=''
		switch(fmat){
			case 'YYYY-MM':
				val=`${Y}-${M}`
				break
			case 'YYYY年MM月':
				val=`${Y}年${M}月`
				break
			default:
				break
		}
		return val
	},
	nuiSetNavigationBarTitle(title){
		uni.setNavigationBarTitle({
		    title: title
		});
	},
	uniRequest(options) {
		let {
			url,
			method,
			params,
			headers,
			success,
			fail,
			isLogin,
			isNoCode,
			complete
		} = options
		this.uniShowLoading({})
		let header={
			'Content-Type': 'application/x-www-form-urlencoded',	
		}
		if(isLogin){
			header=Object.assign(header,headers)
		}else{
			header=Object.assign({
				'Accept':this.uniGetStorage('token') || ''
			},headers,header)
		}
		
		console.log(header)
		uni.request({
			url: baseUrl + url,
			method: method ? method : "GET",
			withCredentials: false,
			data: params,
			header: header,
			success: (res) => {			
				if(res.data.code==0){
					this.uniShowToast({
						title: res.data.msg || "请求失败，请重试",
						icon: "none"
					})
				}else if(res.data.code==1){
					success && success(res.data.data)
				}
				
			},
			fail: (err) => {
				this.uniShowToast({
					title: "请求失败，请重试",
					icon: "none"
				})
				fail ? fail() : false
			},
			complete: () => {		
				this.uniHideLoading()
				complete ? complete() : false
			}
		})
	},
	getTokenValue(options) {
		let {
			success,
			fail,
			complete
		} = options
		var _this = this;
		
	},
	uniShowToast(options) {
		let {
			title,
			icon,
			mask,
			duration,
			image
		} = options
		uni.showToast({
			title: title,
			icon: icon ? icon : "success",
			image: image ? image : "",
			mask: mask ? mask : false,
			duration: duration ? duration : 1500,
			complete: () => {
				setInterval(() => {
					uni.hideToast();
				}, 30000)
			}
		});
	},
	uniRedirectTo(options){
		let {
			url,
			success,
			fail,
			complete
		} = options
		uni.redirectTo({
		    url: url,
		    success: success ? success() : false,
		    fail: fail ? fail() : false,
		    complete: complete ? complete() : false
		});
	},
	uniReLaunch(options){
		let {
			url,
			success,
			fail,
			complete
		} = options
		uni.reLaunch({
		    url: url,
		    success: success ? success() : false,
		    fail: fail ? fail() : false,
		    complete: complete ? complete() : false
		});
	},
	uniSwitchTab(options){
		let {
			url,
			success,
			fail,
			complete
		} = options
		uni.switchTab({
		    url: url,
		    success: success ? success() : false,
		    fail: fail ? fail() : false,
		    complete: complete ? complete() : false
		});
	},
	uniNavigateTo(options) {
		let {
			url,
			animaType,
			time,
			success,
			fail,
			complete
		} = options
		uni.navigateTo({
			url: url,
			animationType: animaType,
			animationDuration: time ? time : 3000,
			success: success ? success() : false,
			fail: fail ? fail() : false,
			complete: complete ? complete() : false
		})
	},
	encodeOrDecode(obj, isEncode = true) {
		if (!obj) return
		let result = isEncode ? encodeURIComponent(JSON.stringify(obj)) : JSON.parse(decodeURIComponent(obj))
		return result
	},
	uniShowLoading(options) {
		let {
			title,
			mask,
			success,
			fail,
			complete
		} = options
		uni.showLoading({
			title: title ? title : '加载中',
			mask: mask ? mask : true,
			success: () => {
				success ? success() : false
			},
			fail: () => {
				fail ? fail() : false
			},
			complete: () => {
				complete ? complete() : false
			},
		})
	},
	uniHideLoading() {
		uni.hideLoading()
	},
	uniSetStorage(key, val) {
		try {
			uni.setStorageSync(key, val);
		} catch (e) {
			console.log(e);
		}
	},
	uniGetStorage(key) {
		try {
			return uni.getStorageSync(key);
		} catch (e) {
			return "fail";
		}
	},
	uniRemoveStorage(key) {
		try {
			uni.removeStorageSync(key);
		} catch (e) {
			console.log(e);
		}
	},
	wxPayMoney(timeStamp,nonceStr,pack,signType,paySign,sucfun,failfun=null,compfun=null){  //封装微信支付函数
		uni.requestPayment({
			provider: 'wxpay',
			orderInfo:"orderInfo",
			timeStamp:timeStamp+'',
			nonceStr: nonceStr,
			package: `prepay_id=${pack}`,
			signType: signType,
			paySign:paySign,
			success (res) {
				sucfun(res)
			},
			fail (err) {
				failfun?failfun(err):false
			},
			complete(result){
				compfun?compfun(result):false
			}
		});
	},	
}
