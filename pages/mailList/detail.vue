<template>
	<view class="wrap">
		<a-navbar  @back="$tool.uniSwitchTab({url:'/pages/mailList/index'})" backgroundColor='transparent' backIconColor="#FFFFFF"></a-navbar>
		<view class="header">
			<!-- <u-icon name="arrow-left" color="#ffffff" size="40" @click.native="$tool.uniSwitchTab({url:'/pages/mailList/index'})" class="back"></u-icon> -->
			<image src='../../static/image/da.png' mode="aspectFill"></image>
			<view class="header-content">	
				<u-avatar src='../../static/image/header.jpg'></u-avatar>
				<view class="text-white right">
					<view class="name">廖术芬</view>
					<view>置业顾问</view>
				</view>
			</view>
		</view>
		<view class="detail">
			<u-form :model="form" ref="uForm" label-position='top'>
				<u-form-item label="姓名">{{form.name1}}</u-form-item>
				<u-form-item label="短号">{{form.name2}}</u-form-item>	
				<u-form-item label="工号">{{form.name3}}</u-form-item>	
				<u-form-item label="区域店铺">{{form.name4}}</u-form-item>	
			</u-form>
		</view>
		<view class="bottom flex a-center j-between flex-row">
			<text class="lint-tar"></text>
			<view class="box flex a-center j-center flex-column" v-for="(item,index) in actionList" :key='index'
				@click="action(item)">
				<view class="icon-box border radius flex a-center j-center">
					<u-icon :name="item.icon" color="#000" size="40"></u-icon>
				</view>
				<view class="name">
					{{item.name}}
				</view>
			</view>			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				actionList:[
					{
						name:"打电话",
						icon:'phone'
					},{
						name:"发短信",
						icon:'chat'
					},
				],
				form:{
					name1:"张三",
					name2:"无",
					name3:"012456",
					name4:"双流区-xxx号店M组",
					phone:'18714516554'
				}
			}
		},
		methods: {
			action(item){
				if(item.icon==='phone'){
					uni.makePhoneCall({
					    phoneNumber: this.form.phone
					});
				}
				
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
		.wrap {
			background: #FFFFFF;
			margin-top: -100rpx;
			.header {
				background: #FFFFFF;
				height: 600rpx;
				position: relative;
			
				.back {
					position: absolute;
					z-index: 9;
					width: 60rpx;
					height: 60rpx;
					padding: 20rpx;
					left: 0rpx;
					top: 10rpx;
				}
			
				image {
					width: 100%;
					height: 100%;
				}
			
				.header-content {
			
					position: absolute;
					bottom: 10rpx;
					width: 100%;
					padding: 20rpx;
					padding-left: 80rpx;
					display: flex;
					align-items: center;
					.name{
						margin-bottom: 10rpx;
					}
					.right{
						margin-left: 30rpx;
					}
				}
			}
			
			.detail {
			
				padding: 20rpx 20rpx;
				/deep/.u-form-item--left__content__label{
					line-height: 40rpx;
					color: #c8c9cc;
				}
				.item {
					border-bottom: 1rpx solid #cecece;
				}
			
				view {
					padding: 10rpx;
				}
			}
			.bottom {
				height: 10%;
				.lint-tar{
					position: absolute;
					width: 1rpx;
					height: 140rpx;
					left: 50%;
					margin-left: -1rpx;
					background: #f4f4f5;
				}
				.box {
					width: 50%;
					padding: 20px 0 ;
					box-sizing: border-box;
					.name{
						margin-top: 20rpx;
					}
					.icon-box{
						width: 66rpx;
						height: 66rpx;
						border: 1rpx solid #c8c9cc;
					}
				}
			}
		}
	}

	
</style>
