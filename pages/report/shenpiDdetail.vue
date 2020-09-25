<template>
	<view class="body">
		<a-navbar title="详情" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="header">
			<view class="title">韩飞提交的申请</view>
			<view class="small">部门</view>
			<view class="status">等我处理</view>
		</view>
		<view class="wrap">
			<view>xxx</view>
			<view>xxxxxxxxx</view>
			<view>xxxx</view>
			<view>xxxxxxxxxxxx</view>
			<view>xxxxxxxxxxxx</view>
			<view>xxxxxxxxxxxxxxxxxx</view>
		</view>
		<view class="bottom">
			<view class='title'>流程</view>
			<view>
				<view class="flex a-center lis">
					<view class="imageCircle">韩非<u-icon name="checkbox-mark" class="icon" color="#00ff00"></u-icon></view>
					<view class="center">
						<view class="title">发起申请</view>
						<view>我</view>
					</view>
					<view class="rights">2020/09/08</view>
				</view>
			</view>
			<view class="line"></view>
			<view>
				<view class="flex a-center lis">
					<view class="imageCircle">韩非<u-icon name="more-dot-fill" class="icon" color="#EE913E"></u-icon></view>
					<view class="center">
						<view class="title">审批人</view>
						<view>我</view>
					</view>
				</view>
			</view>
		</view>
		<view class="bb flex a-center j-between">
			<u-button type="primary" :ripple="true" >拒绝</u-button>
			<u-button type="primary" :ripple="true" @click="show = true">同意</u-button>
		</view>
		<u-modal v-model="show" mode="center" :mask-close-able="true" title="批示" show-cancel-button='true'>
				<u-input type="textarea" class="inp"></u-input>
		</u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show:false,
				numList:[
					{name:'发起申请'},
					{name:'审批人'}
				]
			}
		},
		methods: {
			tong(){
				
			}
		}
	}
</script>

<style lang="scss" scoped>
page{
	.title{
		font-size: 32rpx;
		font-weight: 700;
	}
	.body{
		height: 100%;
		background-color: #EDEDED;
		.header{
			padding:20rpx;
			line-height: 50rpx;
			background: #fff;

			.small{
				color:#adadad;
				
			}
			.status{
				color: #D8A066;
			}
		}
		.wrap{
			margin:80rpx 40rpx;
			background: #fff;
			padding:20rpx;
			border-radius: 20rpx;
			line-height: 80rpx;
		}
		.bottom{
			margin:80rpx 40rpx;
			background-color: #fff;
			padding:20rpx;
			border-radius: 20rpx;
			margin-bottom: 200rpx;
		}
		.bb{
			width: 100%;
			padding:20rpx;
			background-color: #fff;
			position: fixed;
			bottom:0;
			
		}
	}
}
.inp{
	margin-left:20rpx;
}
.icon{
	position: absolute;
	right:-8rpx;
	bottom:10rpx;
	background: #fff;
	border-radius: 50%;
	/* box-shadow: 1px 1px 1px 1px #ddd; */
}
.line{
	border: 1px solid #ddd;
	width:65rpx;
	position: relative;
	left:50rpx;
	transform: rotate(90deg);
}
.imageCircle{
	width:100rpx;
	height:100rpx;
	border-radius: 50%;
	background: #3195FE;
	text-align: center;
	line-height: 100rpx;
	position: relative;
	color: #fff;
	margin: 30rpx;
}
.center{
	line-height: 50rpx;
	margin-right:80rpx;
}
.rights{
	position: relative;
	right:-60rpx;
	top:-20rpx;
}
</style>
