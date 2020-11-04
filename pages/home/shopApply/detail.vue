<template>
	<view class="wrap">
		<a-navbar title="开店详情" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header">
				<image class="head" :src="form.touxiang || '../../../static/image/header.jpg'" mode=""></image>
				<view class="h-right">
					<view>姓名：{{form.ygmingcheng}}</view>
					<view>申请时间：{{form.d_time}}</view>
				</view>
			</view>
			
			<!-- <view class=" details  flex  a-center j-between flex-row">
				<view>职务：<text class="fw">{{form.zhiwu}}</text></view>
			</view>
			<view class="details flex a-center j-between flex-row">
				<view>部门：<text class="fw">{{ form.bumen }}</text></view>
			</view> -->
			<view class=" details flex  a-center j-between flex-row">
				<view>开店类型：<text class="fw">{{form.d_type}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>开店地址：<text class="fw">{{form.d_dizhi}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>房租：{{form.d_fangzu}}<text class="fw"></text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>投资金额：{{form.d_touzi}}<text class="fw"></text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>押金：<text class="fw">{{form.d_yajin}}</text></view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>付款方式：{{form.d_fukuan}}</view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>组数：{{form.d_zu}}</view>
			</view>
			<view class="details flex  a-center j-between flex-row">
				<view>人数：{{form.d_ren}}</view>
			</view>
		</view>
		<view class="gtap"></view>
		<view class="list">
			<u-steps active-color="#78c340" :list="numList" :current="current" direction='column'></u-steps>
		</view>
		<view class='btn'>
			<u-button class="custom-style" @click="backApplication" >撤回申请</u-button>
		</view>
		<u-modal v-model="show" mode="center" @confirm="confirm" @cancel="cancel" :mask-close-able="true" title="" :show-cancel-button="true">
			<view class="t"> 是否撤回？</view>
		</u-modal>
	</view>
</template>

<script>
	import api from "@/utils/api/resign.js"; //api
	export default {
		data() {
			return {
				show:false,
				form:{},
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
				let iThis = this
				api.detail({
					id:this.id
				}).then(res => {
					console.log(res)
					iThis.form = res;
					iThis.current  = res.d_sh 
				})
			},
			confirm(){//确认
				let _this = this
				api.backPost({
					id:this.id,
				}).then(res=> {
					console.log(res);
					_this.$tool.uniShowToast({
						title:res.msg
					})
					_this.$tool.uniSwitchTab({url:`/pages/home/index`})
				})
			},
			backApplication(){//撤回申请
				this.show = true;
			}
		}
	}
</script>

<style lang="scss" scoped>
	page{
		height: 100%;
	}
	.gtap {
		height: 20rpx;
	}
	.t{
		text-align: center;
	}
	.fw {
		
	}

	.custom-style {
		color: #FFFFFF;
		background: #78c340;
	}

	.old {
		color: #b1b1b1;
	}

	.wrap {
		height: 100%;
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
