class Popups {
	constructor() {
		this.outstandingList = [];
		this.typeNum = new Map() //类型及对应数量
	}
	getArrayOutstandingList(array) {
		this.outstandingList = array
	}
	scale() {
		let m = new Map();
		m.set('1', 0);
		m.set('2', 0);
		this.outstandingList.forEach(function(indexs,el) { //计算已选各自个数
			let num = m.get(`${indexs.reasonId}`)*1 ;
			let n = num + 1;
			m.set(`${indexs.reasonId}`, n);
		})
		this.typeNum = m;
	}
	performance(params){
		//金额ysyongjin
		let ysyongjin = params
		let s1 = 60 / 100;
		let s2 = 40 / 100;
		let percentage = {
			"1":0.6,
			"2":0.4
		}
		let arr = this.outstandingList;
		
		for(let i = 0 ; i < arr.length ; i++){
			let type = arr[i].reasonId;
			let all = this.typeNum.get(`${type}`)*1;
			let scale = 1 / all  ;
			let sur = ysyongjin * percentage[`${type}`] * scale ;	

			arr[i].scale = scale.toFixed(2);
			arr[i].outs  = sur.toFixed(2);
		}
		return arr;
	}
}

export default new Popups();
	
	

