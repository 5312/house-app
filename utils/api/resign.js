import tool from '../tool.js'
const request  = tool.uniRequest.bind(tool);//赋值请求方式，并把this指向tool
export  default{
	//调店申请
	bianDong(params){
		return new Promise(function(resolve,reject){
			request({
				url:'rsdangan/biandong',
				method:'post',
				params,
				success:resolve,
			})
		})
	},
	//用户选择
	xuanYongHu(params){
		return new Promise(function(resolve,reject){
			request({
				url:'rsdangan/xuanyonghu',
				method:'GET',
				params,
				success:resolve
			})
		})
	},
	//佣金选择
	yongJinXuanZe(params){
		return new Promise(function(resolve,reject){
			request({
				url:'rsdangan/xuanyongjin',
				method:'GET',
				params,
				success:resolve
			})
		})
	}
}