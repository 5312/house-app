<template>
	<view class="wrap">
		<a-navbar title="员工离店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header">
				<image class="head" :src="form.touxiang" mode=""></image>
				<view class="h-right">
					<view>姓名：{{form.ygmingcheng}}</view>
					<view>申请时间：{{form.bdshijian}}</view>
				</view>
			</view>
			<view class="details flex a-center j-between flex-row">
				<view>离职类型：<text class="fw">{{form.lizhiyy }}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>现职务：<text class="fw">{{form.yzhiwu}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>现店组：<text class="fw">{{form.ybumen}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>是否带走房源：<text class="fw">带走</text></view>
				<view>是否带走客源：<text class="fw">带走</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>房源接收人：<text class="fw">{{form.fzou}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>客源接收人：<text class="fw">{{form.yzou}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>离职生效日期：<text class="fw">{{form.sxshijian}}</text></view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>离职原因：{{form.bdyuanyin}}</view>
			</view>
		</view>
		<view class="gtap"></view>
		<view class="list">
			<u-steps active-color="#78c340" :list="numList" :current="current" direction='column'></u-steps>
		</view>
		<view class='btn'>
			<u-button class="custom-style">撤回申请</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id:null,
				form:{
					name: '刘旭',
					time: '2020/09/17:19:33',
					sex: '男',
					type: "公司调配",
				},
				current:'1',
				numList: [{
						name: '提交申请'
					},
					{
						name: '现店长审核'
					},
					{
						name: '区长审核'
					},
					{
						name: '人事部审核'
					},
					{
						name: '审核完成'
					}
				]
			}
		},
		onLoad(option){
			this.id = option.id;
			this.init();
		},
		methods: {
			init(){
				let params = {
					id:this.id
				}
				let that = this
				this.$tool.uniRequest({
					url: "Dshenpi/lizhi",
					method: 'GET',
					params,
					success: (res) => {
						that.form = res
						that.current  = res.spzt + 1
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
