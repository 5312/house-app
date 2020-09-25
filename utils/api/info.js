import tool from '../tool.js'
const request  = tool.uniRequest.bind(tool);//赋值请求方式，并把this指向tool
export default {
	//审批列表
	ywsp(params){
		return new Promise(function(resolve,reject){
			request({
				url:'ywshenpi/index/ywsp/',
				method:'get',
				params,
				success:resolve,
				fail:reject,
			})
		}) 
	},
	//审批详细
	shengPiXiangQing(params){
		return new Promise(function(resolve,reject){
			request({
				url:'ywshenpi/xiangqing/',
				method:'get',
				params,
				success:resolve,
			})
		})
	},
	//提交审批
	submitShenPi(params){
		return new Promise(function(resolve,reject){
			request({
				url:'ywshenpi/tgshenhe',
				method:'get',
				params,
				success:resolve,
			})
		})
	}
}