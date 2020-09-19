<template>
	<view class="growth-tree">
		<a-navbar title="成长树" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="header header-bg flex a-center j-center flex-column relative" v-if="treeDetail">
			<view class="header-info bg-white absolute border border-radius-8">
				<view class="name">
					{{treeDetail.ygmingcheng}}(No.{{treeDetail.ygbianhao}})
				</view>
				<view class="info-wrap flex a-center j-start flex-row flex-wrap">
					<view class="line flex-shrink" v-for="(item,index) in infoList" :key='index'>
						<text>{{item.name}}</text>:
						<text>{{item.info}}</text>
					</view>
				</view>
			</view>
		</view>
		<view class="content" v-if="list && list.length">
			<u-time-line>
				<u-time-line-item v-for="(item,index) in list">
					<template v-slot:content>
						<view class="border box-temp border-radius-8" :class="[item.type==1 && 'header-bg text-white']">
							<view class="u-order-title">{{index+1}}.{{item.shu_title}}</view>
							<view class="u-order-desc">{{item.shu_msg}}</view>					
						</view>
					</template>
				</u-time-line-item>				
			</u-time-line>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				treeDetail:null,
				list:[],
				infoList: [{
					info: "",
					prop:"gangwei",
					name: "职务"
				}, {
					info: "",
					prop:"dianhua",
					name: "电话"
				}, {
					info: "",
					prop:"rzshijian",
					name: "入职时间"
				}, {
					info: "",
					prop:"htzhuangtai",
					name: "合同阶段"
				}]
			}
		},
		onLoad(){
			this.init()
		},
		methods:{
			init(){
				this.$tool.uniRequest({
					url:"rbac/chengzhangshu/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId')
					},
					success:(res)=>{
						this.treeDetail=res
						this.infoList.forEach(item=>{
							if(res.hasOwnProperty(item.prop) ){
								item.info=res[item.prop]
							}
						})
						this.list=res.chengzhang || []
					}
				})
			},
		}
	}
</script>

<style scoped lang="scss">
	.header-bg{
		background-color: #19be6b;
	}
	.growth-tree {
		.header {
			height: 300rpx;
			.header-info {
				height: 260rpx;
				left: 50%;
				width: 680rpx;
				margin-left: -340rpx;
				top: 100rpx;
				padding: 40rpx;
				box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
				.name{
					color: #19be6b;
				}
				.info-wrap {
					margin-top: 24rpx;

					.line {
						width: 50%;
						font-size: 25rpx;
						line-height: 2;
						margin-bottom: 10rpx;
					}
				}
			}
		}
		.content{
			padding: 40rpx 80rpx;
			margin-top: 80rpx;
			font-size: 40rpx;
			.box-temp{
				padding: 20rpx;
				font-size: 24rpx;
			}
		}
	}
</style>
