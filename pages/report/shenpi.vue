<template>
	<view>
		<a-navbar title="审批" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<u-tabs-swiper ref="tabs" active-color="#00ff7f" :current='current' bar-width="150"  :list="list" :is-scroll="false"></u-tabs-swiper>
		<view class="wrap">
			<view v-for="(x,y) in detailList" :key='y'>
				<view class="detail">
					<view class="title">{{x.to_title}}</view>
					<view class="time">{{x.addtime}}</view>
					<view class="time" v-html="x.neirong"></view>
					<view class="bottom">
						<view class="left"><u-icon name='home-fill' class="ic" ></u-icon>{{x.bumen}}</view>
						<u-tag text="审批失败" type="info" shape="circle"></u-tag>
					</view>
				</view>
				<view class="hi"></view>
			</view>
		</view>
	</view>
</template>

<script>
	import api from '@/utils/api/info.js'
	export default {
		data() {
			return {
				current:0,
				detailList:[],
				totalcount:'',
				totalpage:'',
				intPage:1,
				list: [{
					name: '我的审批'
				}, {
					name: '我提交的审批'
				}],
			}
		},
		onLoad(){
			this.request()//审批列表
			
		},
		methods: {
			request(){
				let _this = this;
				let data = {
					type:'',//
					p:_this.intPage//页码
				};
				api.ywsp(data).then(res => {
					res.list[0].neirong = `<li style=''>离职原因:123123</li>`
					_this.detailList = res.list;
					
					_this.totalcount = res.totalcount//总共条数
					_this.totalpage = res.totalpage//总共页数
					_this.intPage = res.intPage//当前页码
					console.log(res)
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	page{
		background-color:  #EDEDED;
	}
	.hi{
		height: 20rpx;
		background: #EDEDED;
		width:200%;
	}
	.wrap{
		margin:40rpx;
		background: #fff;
		padding-top:10rpx;
		.detail{
			line-height: 40rpx;
			margin:40rpx;
			margin-bottom:10rpx;
			.time{
				font-size: 24rpx;
				color:#bebebe;
			}
		}
		.title{
			font-size: 32rpx;
			font-weight: bold;
		}
		.bottom{
			text-align: right;
			padding:10rpx;
			border-top:1px solid #ddd;
			padding-top:20rpx;
			display: flex;
			justify-content: space-between;
			.ic{
				margin-right:20rpx;
			}
			.right{
				color: #D8A066;	
			}
		}
	}
</style>
