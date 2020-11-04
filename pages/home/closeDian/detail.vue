<template>
	<view class="wrap">
		<u-toast ref="uToast" />
		<view class="content">
			<view class="header">
				<image class="head" :src="data.touxiang || '../../../static/image/header.jpg'" mode=""></image>
				<view class="h-right">
					<view>负责人：{{data.fuzeren}}</view>
					<view>申请时间：{{data.close_time}}</view>
				</view>
			</view>
			<!-- <view class=" details  flex  a-center j-between flex-row">
				<view>名称：<text class="fw">{{data.ygmingcheng}}</text></view>
			</view>
			<view class=" details  flex  a-center j-between flex-row">
				<view>开店时间：<text class="fw">{{data.dian_kaitime}}</text></view>
			</view>
			<view class=" details  flex  a-center j-between flex-row">
				<view>闭店时间：<text class="fw">{{data.close_time}}</text></view>
			</view>
			<view class=" details  flex  a-center j-between flex-row">
				<view>职务：<text class="fw">{{data.zhiwu}}</text></view>
			</view> -->
			<!-- <view class="details flex a-center j-between flex-row">
				<view>部门：<text class="fw">{{ data.bumen }}</text></view>
			</view> -->
			<view class=" details flex  a-center j-between flex-row">
				<view>店类型：<text class="fw">{{data.d_type}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>关闭店：<text class="fw">{{data.bmming}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>店地址：<text class="fw">{{data.bmdizhi}}</text></view>
			</view>
			<view class=" details flex  a-center j-between flex-row">
				<view>关闭原因：<text class="fw">{{data.yuanyin}}</text></view>
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
		name:'detail',
		props:{
			// 点击遮罩是否可以关闭actionsheet
			data: {
				type: Object,
				default: ''
			},
		},
		data() {
			return {
				show:false,
				current:'0',
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
			this.current = this.data.d_sh;
		},
		methods: {
			confirm(){//确认
				let _this = this
				api.backCloseDetail({
					id:this.data.id,
				}).then(res=> {
					this.$refs.uToast.show({
						title: res.msg,
						type: 'success',
						isTab:true,
						url:'/pages/home/index'
					})
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
