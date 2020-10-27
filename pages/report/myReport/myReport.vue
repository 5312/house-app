<template>
	<view>
		<u-navbar back-text="返回" title="我的报备" :height='50' :background='background'>
			<view slot="right" class="slot-right" style="padding-right: 20px;" @click="newReport">
				新增报备
			</view>
		</u-navbar>
		<view class="content">
			<!-- 正文内容 -->
			<u-tabs :list="lists" active-color='#07c160' inactive-color='#000' :is-scroll="false" :current="current" @change="change"></u-tabs>
			<view class="search"><u-search 
									placeholder="新房推广名/楼盘名称" 
									v-model="keyword"
									:show-action="true"
									:action-text="right"></u-search>
			</view>
			<view class="main">
				<view v-for="(x,y) in list " :key='y' class="unity">
					<view class="title">{{ x.title }}</view>
					<view><text class="gray">客户信息：</text><text class="black">成哥 78451212</text></view>
					<view><text class="gray">到访信息：</text><text class="black">2020.04.05 14：02</text></view>
					<view><text class="gray">经纪人：</text><text class="black">虹桥一店</text></view>
					<view class="flex a-center j-between bottom" >
						<view :style="{ color:color }">{{ manage }}</view>
						<view class="gray">2020.08.28 13:42</view>
					</view>
					<view class="b-btn">
						<u-button class="btn" shape="circle" size="mini" :ripple="true" ripple-bg-color="#909399">修改</u-button>
						<u-button class="btn" shape="circle" size="mini" :ripple="true" ripple-bg-color="#909399">修改</u-button>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				background:{
					backgroundColor:'#EDEDED'
				},
				right:'筛选',
				color:'#9AB3C7',//#00B67E//#C23359
				keyword:'',
				current:0,
				lists:[{
						name: '报备（111）'
					},
					{
						name: '带看（60）'
					}
				],
				list:[
					{
						title:'外滩一号',
						
					},
					{
						title:'外滩一号',
						
					}
				]
			}
		},
		computed:{
			manage(){
				return '报备确认中'
			}
		},
		methods: {
			newReport(){
				console.log('newReport')
			},
			change(index){
				this.current = index;
			},
			custom(){
				console.log();
			}
		}
	}
</script>

<style lang="scss" scoped>
	page{
		background-color: #F4F7FD;
	}
.slot-right{
	padding: 0 40rpx;
}
.content{
	.search{
		padding:20rpx 40rpx;
		background:#FAFAFA;
	}
	.main{
		
		padding:20rpx;
		.unity{
			padding:20rpx;
			background: #fff;
			border-radius: 10rpx;
			margin-bottom:20rpx;
			font-size: 28rpx;
			.title{
				color:#000;
				font-size: 32rpx;
				font-weight: 600;
			}
			.gray{
				color:#C2C5CD;
			}
			.black{
				color: black;
				
			}
			.bottom{
				padding:20rpx 0;
				border-bottom: 1px solid #C2C5CD;
			}
			.b-btn{
				padding:20rpx;
				text-align: right;
				.btn{
					margin:0 20rpx;
				}
			}
		}
	}
}
</style>
