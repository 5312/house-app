<template>
	<view class="wrap">
		<a-navbar title="员工离职申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header">
				<image class="head" :src="form.touxiang || '../../../static/image/header.jpg'" mode=""></image>
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
				<view>银行卡类型：<text class="fw">{{ form.bankname }}</text></view>
			</view> 
			<view class=" details flex  a-center j-between flex-row">
				<view>银行卡号：<text class="fw">{{ form.banknum}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>房源接收人：<text class="fw">{{form.fzou}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>客源接收人：<text class="fw">{{form.yzou}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>离职生效日期：<text class="fw">{{form.lizhitime}}</text></view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>离职原因：{{form.bdyuanyin}}</view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>档案是否在公司：{{form.dashifou}}</view>
			</view>
		</view>
		<view class="gtap"></view>
		<view class="list">
			<u-steps active-color="#78c340" :list="numList" :current="current" direction='column'></u-steps>
		</view>
		<view class='btn'>
			<u-button class="custom-style" @click="backApplication">撤回申请</u-button>
		</view>
		<u-modal v-model="show" mode="center" @confirm="confirm" @cancel="cancel" :mask-close-able="true" title=""
		 :show-cancel-button="true">
			<view class="t"> 是否撤回？</view>
		</u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				id: null,
				form: {
					name: '刘旭',
					time: '2020/09/17:19:33',
					sex: '男',
					type: "公司调配",
					touxiang:'../../../static/image/header.jpg'
				},
				current: '1',
				numList: [{
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
				]
			}
		},
		onLoad(option) {
			this.id = option.id;
			this.init();
		},
		methods: {
			
			init() {
				let params = {
					id: this.id
				}
				let that = this
				this.$tool.uniRequest({
					url: "Dshenpi/lizhi",
					method: 'GET',
					params,
					success: (res) => {
						console.log(res)
						that.form = res
						that.current = res.spzt
					}
				})
			},
			cancel(){//取消
				
			},
			confirm() { //确认
				let _this = this;
				this.$tool.uniRequest({
					url: 'rsdangan/backsq',
					method: 'GET',
					params: {
						id: this.id
					},
					success: function(res, msg) {
						_this.$tool.uniShowToast({
							title: msg.msg
						})
						_this.$tool.uniSwitchTab({
							url: `/pages/home/index`
						})
					}
				})
			},
			backApplication() { //撤回申请
				this.show = true;
			}
		}
	}
</script>

<style lang="scss" scoped>
	page{
		height:100%;
	}
	.gtap {
		height: 20rpx;
	}
	.t{
		text-align: center;
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
		height:100%;
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
