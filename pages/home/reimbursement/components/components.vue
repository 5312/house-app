<template>
	<view class="formWrap">
		<a-navbar title="返回" @back="back"></a-navbar>
		<u-form v-if="types != 2" :model="form" ref="uForm">
			<u-form-item  label-width='150' label-align='left' label="费用项目:">
				<u-input v-model="form.project" @click='proj = true' placeholder="请选择" type="select" />
				<u-select v-model='proj' value-name='lxid' label-name='lxming' :list='projectList' @confirm='projectFun'></u-select>
			</u-form-item>
			<u-form-item label-width='150' label-align='left' label="缴费人:">
				<u-input v-model="form.pay" type="select" placeholder="请选择" @click='payp = true'></u-input>
				<u-select v-model='payp' :list='option' @confirm='payaFun'></u-select>
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="金额:">
				<u-input v-model="form.price" placeholder="请输入金额" type="number" />
			</u-form-item>

			<u-button @click="add">添加</u-button>
		</u-form>
		
		<u-form v-if="types == 2" :model="form" ref="uForm">
			<u-form-item  label-width='150' label-align='left' label="经济人:">
				<u-input v-model="form.people" @click='peop = true' placeholder="请选择" type="select" />
				<u-select v-model="peop" value-name='uid' label-name='ygmingcheng' :list="peopleList" @confirm='peopleFun'></u-select>
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="分成理由:">
				<u-input v-model="form.reason" @click='rea = true' placeholder="请选择" type='select' />
				<u-select v-model="rea"  :list="reasonList" @confirm='reasonFun'></u-select>
			</u-form-item>
			
			<u-form-item  label-width='150' label-align='left' label="分成比例:">
				<u-input v-model="form.scale" type='number' placeholder="" :disabled="true" />
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="分成业绩:">
				<u-input v-model="form.outs" type='number' placeholder=""  :disabled="true" />
			</u-form-item>
			<u-button @click="add">添加</u-button>
		</u-form>
	</view>
</template>

<script>
	import api from '@/utils/api/resign.js';
	import unit from '../../../../utils/unit.js'
	export default {
		name: 'comadd',
		props: {
			types: {
				type:Number,
				default:0
			},
			outstandingList:{
				type:Array,
				default:[],
			},
			ysyongjin:{
				type:Number,
				default:0
			}
		},
		data() {
			return {
				proj:false,
				peop:false,
				rea:false,
				payp:false,
				option:[
					{"value":1,"label":'业主'},
					{"value":2,"label":'客户'}
				],
				form:{
					type:1,
					show:false,//用做add页面删除功能
				},
				reasonList:[
					{"value":1,"label":'房源'},
					{"value":3,"label":'客源'}
				],
				peopleList:[],
				projectList:[],
			}
		},
		mounted() {
			// console.log("调用了mounted");
		},
		created(){
			let data = {};
			let aThis = this;
			api.xuanYongHu(data).then( res => {
				aThis.peopleList = res;
			});
			let params = {
				type:this.types
			}
			api.yongJinXuanZe(params).then( res => {
				aThis.projectList = res;
			})
		},
		methods: {
			reasonFun(val){
				
				this.form.reason = val[0].label
				this.form.reasonId = val[0].value;

				let arr = this.outstandingList;
				let tt = new Map();
				tt.set('1',0);
				tt.set('3',0);

				arr.forEach(function(item){//计算已选各自个数
					if(item.reasonId == 1){
						let num = tt.get('1')*1 + 1
						tt.set('1',num)
					}else{
						let num = tt.get('3')*1 + 1
						tt.set('3',num)
					}
				})
				//金额ysyongjin
				let s1 = 60 / 100;
				let s2 = 40 / 100;
				//理由选择后计算
				if(val[0].value == 1){//刚选的
					let num = tt.get('1')*1 + 1
					tt.set('1',num);
					//
					let all = tt.get('1')*1;
					let scale = 1 / all  ;
					let sur = this.ysyongjin * s1 * scale ;
					
					console.log(all,scale,sur)
					this.form.scale = scale.toFixed(2);
					this.form.outs  = sur.toFixed(2);
				}
				if(val[0].value == 3){
					let num = tt.get('3')*1 + 1
					tt.set('3',num)
					
					let all = tt.get('3')*1;
					let scale = 1 / all ;
					let sur = this.ysyongjin * s2 * scale;
					this.form.scale = scale.toFixed(2);
					this.form.outs  = sur.toFixed(2);
				}
			},
			payaFun(val){
				this.form.pay = val[0].label
				this.form.payid = val[0].value
			},
			projectFun(val){
				this.form.project = val[0].label
				this.form.projectId = val[0].value;
			},
			peopleFun(val){
				this.form.people = val[0].label.split('-')[1]
				this.form.peopleId = val[0].value;
			},
			back(){
				this.$emit('back');
			},
			add() {
				if(this.types != 2){
					if(this.form.project === undefined) {
						this.$u.toast(`请选择费用项目`);
						return 
					} 
					if(this.form.pay === undefined) {
						this.$u.toast(`请选择缴费人`);
						return 
					} 
					if(this.form.price === undefined) {
						this.$u.toast(`请输入金额`);
						return 
					} 
				}else{
					if(this.form.reason === undefined){
						this.$u.toast(`请输入分成理由`);
						return 
					}
				}
				this.$emit('update',this.form)	
			}
		}
	}
</script>

<style lang="scss" scoped>
.formWrap{
	padding:0 40rpx;
}
</style>
