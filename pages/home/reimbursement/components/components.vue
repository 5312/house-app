<template>
	<view class="formWrap">
		<u-form v-if="types == 0" :model="form" ref="uForm">
			<u-form-item  label-width='150' label-align='left' label="费用项目:">
				<u-input v-model="form.project" @click='proj = true' type="select" />
				<u-select v-model='proj' value-name='xsshunxu' label-name='lxming' :list='projectList' @confirm='projectFun'></u-select>
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="金额:">
				<u-input v-model="form.price" />
			</u-form-item>

			<u-button @click="add">添加</u-button>
		</u-form>
		
		<u-form v-if="types == 1" :model="form" ref="uForm">
			<u-form-item  label-width='150' label-align='left' label="经济人:">
				<u-input v-model="form.people" @click='peop = true' type="select" />
				<u-select v-model="peop" value-name='uid' label-name='ygmingcheng' :list="peopleList" @confirm='peopleFun'></u-select>
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="分成理由:">
				<u-input v-model="form.reason" @click='rea = true' type='select' />
				<u-select v-model="rea"  :list="reasonList" @confirm='reasonFun'></u-select>
			</u-form-item>
			
			<u-form-item  label-width='150' label-align='left' label="分成比例:">
				<u-input v-model="form.scale" placeholder="" :disabled="true" />
			</u-form-item>
			<u-form-item  label-width='150' label-align='left' label="分成业绩:">
				<u-input v-model="form.outs" placeholder=""  :disabled="true" />
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
				type:String,
				default:""
			}
		},
		data() {
			return {
				proj:false,
				peop:false,
				rea:false,
				form:{
					type:1,
					show:false,//用做add页面删除功能
				},
				reasonList:[
					{"value":1,"label":'房源录入'},
					{"value":2,"label":'客源录入'}
				],
				peopleList:[],
				projectList:[],
			}
		},
		mounted() {
			 console.log("调用了mounted");
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
				this.form.type = 2;
				
				this.form.reason = val[0].label
				this.form.reasonId = val[0].value;

				let arr = this.outstandingList;
				let tt = new Map();
				tt.set('1',0);
				tt.set('2',0);

				arr.forEach(function(item){//计算已选各自个数
					if(item.reasonId == 1){
						let num = tt.get('1')*1 + 1
						tt.set('1',num)
					}else{
						let num = tt.get('2')*1 + 1
						tt.set('2',num)
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
				if(val[0].value == 2){
					let num = tt.get('2')*1 + 1
					tt.set('2',num)
					
					let all = tt.get('2')*1;
					let scale = 1 / all ;
					let sur = this.ysyongjin * s2 * scale;
					this.form.scale = scale.toFixed(2);
					this.form.outs  = sur.toFixed(2);
				}
			},
			projectFun(val){
				this.form.project = val[0].label
				this.form.projectId = val[0].value;
			},
			peopleFun(val){
				this.form.people = val[0].label.split('-')[1]
				this.form.peopleId = val[0].value;
			},
			add() {
				//console.log(this.types)
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
