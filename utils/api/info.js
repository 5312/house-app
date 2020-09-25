import tool from '../tool.js'
const request  = tool.uniRequest.bind(tool);
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
	}
}