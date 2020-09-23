<template>
	<view class="wrap">
		<a-navbar title="员工调店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header">
				<image class="head" src="../../../static/image/header.jpg" mode=""></image>
				<view class="h-right">
					<view>姓名：{{form.ygmingcheng}}</view>
					<view>申请时间：{{form.bdshijian}}</view>
				</view>
			</view>

			<view class=" details old flex  a-center j-between flex-row">
				<view>原职务：<text class="fw">{{form.yzhiwu}}</text></view>

			</view>
			<view class=" details old flex  a-center j-between flex-row">
				<view>原店组：<text class="fw">{{form.ybumen}}</text></view>

			</view>
			<view class="details flex a-center j-between flex-row">
				<view>申请类型：<text class="fw">{{ form.diao }}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>现职务：<text class="fw">{{form.diaodongzw}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>现店组：<text class="fw">{{form.diaodongbm}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>是否带走房源：<text class="fw">带走</text></view>
				<view>是否带走客源：<text class="fw">带走</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>房源接收人：{{form.fzou}}<text class="fw"></text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>客源接收人：{{form.yzou}}<text class="fw"></text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">

				<view>调店生效日期：<text class="fw">{{form.sxshijian}}</text></view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>申请原因：{{form.bdyuanyin}}</view>
			</view>
		</view>
		<view class="gtap"></view>
		<view class="list">
			<u-steps active-color="#78c340" :list="numList" :current="current" direction='column'></u-steps>
		</view>
		<view class='btn'>
			<u-button class="custom-style" @click="backApplication">撤回申请</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{
					ygmingcheng: '刘旭',
					bdshijian: '2020/09/17:19:33',
					lizhiyy: "公司调配",
					"yzhiwu": "资深经纪人",      //原职务
				},
				current:'1',
				numList: [
					{
						name: '店长审核'
					},
					{
						name: '区长审核'
					},
					{
						name: '人事审核'
					},
					{
						name: '审核完成'
					}
				],
				id: null,
			}
		},
		onLoad(option) {
			this.id = option.id;
			this.init();
		},
		methods: {
			init() {
				let params = {
					id:this.id
				}
				let that = this
				this.$tool.uniRequest({
					url: "Dshenpi/biandong",
					method: 'GET',
					params,
					success: (res) => {
						console.log(res)
						that.form = res
						that.current  = res.spzt 
					}
				})
			},
			backApplication(){//撤回申请
				let _this = this
				this.$tool.uniRequest({
					url:'rsdangan/backsq',
					method:'GET',
					params:{
						id:this.id
					},
					success:function(res,msg){
						
						_this.$tool.uniShowToast({
							title:msg.msg
						})
						_this.$tool.uniSwitchTab({url:`/pages/home/index`})
					}
				})
				
			}
		}
	}
</script>

<style lang="scss" scoped>
	.gtap {
		height: 20rpx;
	}

	.fw {
		font-weight: 700;
	}

	.custom-style {
		color: #FFFFFF;
		background: #78c340;
	}

	.old {
		color: #b1b1b1;
	}

	.wrap {
		background-color: #EDEDED;
		padding-bottom: 20rpx;

		.content {
			background-color: #fff;

			.header {
				padding: 30rpx;

				.head {
					width: 150rpx;
					height: 150rpx;
					border-radius: 50%;
				}

				.h-right {
					font-weight: 700;
					height: 150rpx;
					line-height: 70rpx;
					margin-left: 30rpx;
					display: inline-block;
					vertical-align: top;
				}
			}

			.details {
				padding: 10rpx 30rpx;
			}
		}

		.list {
			background-color: #fff;
			padding: 0 100rpx;
		}

		.btn {
			width: 50%;
			margin: 30rpx auto 0;
		}
	}
</style>
