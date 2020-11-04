<template>
<view class="main">
<a-navbar title="闭店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
<!-- 内容 -->
	<view class="wrap" v-if='!data'>
	<u-toast ref="uToast" />
	<u-card 
	v-for="item,index in list" 
	:key="index+'-card'"
	:title="item.type_name" 
	:sub-title="item.dian_kaitime" 
	:foot-style='foot'
	>
		<view class="" slot="body">
			<view class="card-name">店铺名称：{{item.bmming}}</view>
			<view class="card-people">负责人：{{item.fuzeren}}</view>
			<view class="card-address">店铺地址：{{item.bmdizhi}}</view>
		</view>
		<view slot="foot">
			<u-collapse hover-class='active'
			:head-style='headStyle'
			>
				<u-collapse-item 
				:title="'我要关店'" 
				v-for="(x,y) in arr"
				:key="y+'-coll'"
				>
				<view class='card'>
					<!-- <label for="" style="padding:0 40rpx;color:#000">关店原因</label> -->
					<u-field
					v-model="item.cause"
					label=""
					label-width="0"
					type="textarea"
					placeholder="请输入关店原因"
					required
					:error-message="item.msg"
					@input="inputChange(index,item.cause)"
					>
					</u-field>
					<u-button class="btn" size="default" type="success" @click="submit(item)">提交</u-button>
				</view>
				</u-collapse-item>
			</u-collapse>
		</view>
		
	</u-card>

	</view>
	<detail v-if="data" :data='data'></detail>
</view>
</template>

<script>
	import api from "@/utils/api/resign.js"; //api
	import detail from './detail';
	export default {
		components:{
			detail
		},
		data() {
			return {
				cause:null,
				data:null,
				form:{
					
				},
				headStyle:{
					color:'#19be6b'
				},
				arr:[{
					name:'提交'
				}],
				foot:{
					padding:'0 16px',
				},
				list: []
			}
		},
		onLoad() {
			this.getApplication();
		},
		methods: {
			submit(item){
				if(!item.cause){
					this.$refs.uToast.show({
						title: '请输入关店原因',
						type: 'warning',
					})
					return;
				}
				let sThis = this;
				api.closeDian({
					dian_id: item.id, //店铺id
					yuanyin: item.cause//关店原因
				},'POST').then(result => {
					this.$refs.uToast.show({
						title: '提交成功',
						type: 'success',
					})
					setTimeout(()=>{
						api.closeDetail({
							id:result.id
						}).then(res => {
							res.data.id = result.id
							sThis.data = res.data
							console.log(sThis.data)
						})
					},2000)
				})
			},
			inputChange(index,cause){
				if(cause){
					this.list[index].msg = '';
				}
			},
			//关店申请
			getApplication() {
				let gThis = this;
				//get请求
				api.closeDian({
					dian_id: '', //店铺id,get不传
					yuanyin: '' //关店原因
				}, 'GET').then(res => {
					if (res == 0) {
						setTimeout(() => {
							gThis.$tool.uniSwitchTab({
								url: '/pages/home/index',
							})
						}, 2000)
					}else if(res.id){
						return res
					} else {
						res.forEach(function(item){
							item.msg = '请输入关店原因';
						})
						gThis.list = res;
					}
				}).then(resId => {
					if(resId){
						api.closeDetail({
							id:resId.id
						}).then(res => {
							res.data.id = resId.id
							gThis.data = res.data
						})
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
.main{
	padding:0 20rpx;
	.card{
		overflow-x: auto;
		height: auto;
		.btn{
			margin:20rpx auto;
			
		}
	}
	
}
</style>
