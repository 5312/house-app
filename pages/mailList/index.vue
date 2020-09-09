<template>
	<view class="mail-list">
		<u-navbar :background="{backgroundColor: '#EDEDED'}" title="" :is-back="false" :height='50' :border-bottom='false'>
			<view class="header flex j-between a-center flex-row">
				<view class="left padding-lr-40">
					<u-icon name="list" size="50"></u-icon>
				</view>
			</view>
		</u-navbar>
		<view class="content">
			<u-index-list :scrollTop="scrollTop">
				<view v-for="(item, index) in indexList" :key="index">
					<u-index-anchor :index="item.index" />
					<view v-for="(x,y) in item.key" :key="y" class="list-cell" @click="toDetail(item)">
						<image class="leftHead" :src="x.img" mode=""></image>
						<view class="textname">
							<view class="name">{{x.name}}</view>
							<text class="tel">{{x.tel}}</text>
							<text class="detail">{{x.detail}}</text>
						</view>
						<u-icon name="arrow-right" color="#c8c9cc" size="28"></u-icon>
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
				/* indexList: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
					"V", "W", "X", "Y", "Z"
				], */
				indexList: [
					{
						"index":'A',
						"key": [
							{
							a: 'A',
							name: "安鹏理-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						},
						{
							a: 'A',
							name: "安鹏理-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						},
						{
							a: 'A',
							name: "安鹏理-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						}
						] 
					},
					{
						"index":'B',
						"key": [{
							a: 'B',
							name: "夲许-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						}] 
					},{
						"index":'C',
						"key": [{
							a: 'C',
							name: "慈禧-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						}] 
					},
					{
						"index":'H',
						"key": [{
							a: 'H',
							name: "黄金许-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						},{
							a: 'H',
							name: "黄金许-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						},{
							a: 'H',
							name: "黄金许-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						}] 
					},
					{
						"index":'L',
						"key": [{
							a: 'L',
							name: "刘-双流区-香墅2  号店M组",
							tel: "18700003333(无)",
							detail: "实习置业顾问",
							img: "../../static/image/header.jpg"
						}] 
					},
				]
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onLoad() {

		},
		methods: {
			toDetail(item){
				this.$tool.uniNavigateTo({
					url:"/pages/mailList/detail"
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
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
		.leftHead{
			width: 100rpx;
			height: 100rpx;
			border-radius: 50%;
		}
		.textname{
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
