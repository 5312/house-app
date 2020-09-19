<template>
	<view class="mail-list">
		<u-navbar :background="{backgroundColor: '#EDEDED'}" title="" :is-back="false" :height='50' :border-bottom='false'>
			<view class="header flex j-center text-center a-center flex-row w100">
				通讯录
			</view>
		</u-navbar>
		<view class="content">
			<u-index-list :scrollTop="scrollTop" :indexList="abcList">
				<view v-for="(item, index) in indexList" :key="index" >
					<u-index-anchor :index="item.index" />
					<view v-for="(x,y) in item.key" :key="y" class="list-cell" @click="toDetail(x)">
						<!-- <image class="leftHead" src="../../static/image/header.jpg" mode=""></image> -->
						<u-image class="leftHead" width="100rpx" shape="circle"  height="100rpx" :src="x.touxiang || moren" :lazy-load="true"></u-image>
						<view class="textname">
							<view class="name">{{x.ygmingcheng}}-{{x.bumen}}</view>
							<text class="tel">{{x.dianhua}}</text>
							<text class="detail">{{x.gangwei}}</text>
						</view>
						<u-icon name="arrow-right" class="flex-shrink" color="#c8c9cc" size="28"></u-icon>
					</view> 
				</view>
			</u-index-list>
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				scrollTop: 0,
				indexList: null,
				abcList:[],
				moren:'../../static/image/header.jpg'
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onLoad() {
			this.init()
		},
		methods: {
			init(){
				this.$tool.uniRequest({
					url:"rbac/tongxun/",
					method:'GET',									
					success:(res)=>{
						let arr=res
						this.abcList=[]
						arr.forEach(item=>{
							this.abcList.push(item.shou)						
						})
						let set=new Set(this.abcList)
						this.abcList=[...set]
						this.abcList.sort()
						this.indexList=[]
						this.abcList.forEach(item=>{
							let obj={
								'index':item,
								"key":[]
							}
							arr.forEach(item1=>{
								if(item===item1.shou){
									obj['key'].push(item1)
								}
							})
							this.indexList.push(obj)
						})
					}
				})
			},
			toDetail(item){
				this.$tool.uniNavigateTo({
					url:`/pages/mailList/detail?userId=${item.id}`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.header{
		font-size: 32rpx;
		font-weight: 700;
	}
	.list-cell {
		display: flex;
		box-sizing: border-box;
		justify-content: space-between;
		width: 100%;
		padding: 10px 24rpx;
		padding-right: 40rpx;
		overflow: hidden;
		color: #323233;
		font-size: 14px;
		line-height: 24px;
		background-color: #fff;
		align-items: center;
		border-bottom:1px solid #ddd;
		.leftHead{
			// width: 100rpx;
			// height: 100rpx;
			border-radius: 50%;
			flex-shrink: 0;
			margin-right: 40rpx;
		}
		.textname{
			flex: 1;
			margin-right: 20rpx;
			.name{
				font-size: 32rpx;
				font-weight: 600;
				margin-bottom: 10rpx;
			}
			.tel{
				color: #39c7c7;
			}
			.detail{
				color:#949494;
				margin-left: 20rpx;
			}
		}
		.more{
			width: 40rpx;
			height:40rpx;
		}
	}
</style>
