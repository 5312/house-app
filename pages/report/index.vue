<template>
	<view class="report relative">
		<u-navbar :is-back="false" :background="{backgroundColor: '#EDEDED'}" :height='50'>
			<view class="slot-wrap header flex a-center j-start flex-row">
				<u-search placeholder="请输入搜索内容" v-model="searchVal" :clearabled="true" bg-color="#fff" :show-action="false" ></u-search>
				<u-icon name="search" color="#ffffff" size="56" class="margin-l-30"></u-icon>
			</view>
		</u-navbar>
		<view class="content">
			<view>
				<u-dropdown-list :dropdownList='dropdownList' ></u-dropdown-list>
				<u-notice-bar mode="horizontal" :list="list"></u-notice-bar>
			</view>
			<view class="list-wrap">
				<view v-for="(x,y) in shop" class="list border-bottom flex a-start j-start flex-row" @click="toPage(x)">
					<view class="left flex-shrink">
						<image :src="x.src" class="img"></image>
						<view class="posi">
							住宅好房
						</view>
					</view>
					<view class="right">
						<view class="title">{{x.name}}</view>
						<view class='tags'>
							<u-tag class="tag" v-for="item in x.tag" size='mini' mode="dark" :text="item.name" :type="item.type" />
						</view>
						<view class="detail">
							<view>装修：精装</view>
							<view>总价(万)：29-70</view>
							<view>产权年限(年)：70</view>
						</view>
						<view></view>
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
				searchVal:'',
				dropdownList:[
					{ show: false, options: [
						{ id: 0, text: '区域项目', value: '', select: false },
						{ id: 1, text: '全场券', value: 1, select: false },
						{ id: 2, text: '品类券', value: 2, select: false },
						{ id: 3, text: '单品券', value: 3, select: false },
						{ id: 4, text: '业务券', value: 4, select: false }
					] },
					{ show: false, options: [
						{ id: 0, text: '产品类型', value: '', select: false },
						{ id: 1, text: '全场券', value: 1, select: false },
						{ id: 2, text: '品类券', value: 2, select: false },
						{ id: 3, text: '单品券', value: 3, select: false },
						{ id: 4, text: '业务券', value: 4, select: false }
					] },
					{ show: false, options: [
						{ id: 0, text: '筛选条件', value: '', select: false },
						{ id: 1, text: '全场券', value: 1, select: false },
						{ id: 2, text: '品类券', value: 2, select: false },
						{ id: 3, text: '单品券', value: 3, select: false },
						{ id: 4, text: '业务券', value: 4, select: false }
					] }
				],
				shop: [{
						name: "峨眉悦山府（还有好东西",
						src: '../../static/image/tang.jpg',
						tag: [{
								name: '自助',
								type: 'primary'
							},
							{
								name: '文化',
								type: 'warning'
							}, {
								name: '新人',
								type: 'success'
							}
						]
					},
					{
						name: "峨眉悦山府（还有好东西xxxxxxxxxxxxxxxx)",
						src: '../../static/image/tang.jpg',
						tag: [{
								name: '自助',
								type: 'primary'
							},
							{
								name: '文化',
								type: 'warning'
							}, {
								name: '新人',
								type: 'success'
							}
						]
					},
					{
						name: "峨眉悦山府（还有好东西",
						src: '../../static/image/tang.jpg',
						tag: []
					},
					{
						name: "峨眉悦山府（还有好东西",
						src: '../../static/image/tang.jpg',
						tag: [{
								name: '自助',
								type: 'primary'
							},
							{
								name: '文化',
								type: 'warning'
							}, {
								name: '新人',
								type: 'success'
							}
						]
					},
					{
						name: "峨眉悦山府（还有好东西",
						src: '../../static/image/tang.jpg',
						tag: [{
								name: '自助',
								type: 'primary'
							},
							{
								name: '文化',
								type: 'warning'
							}, {
								name: '新人',
								type: 'success'
							}
						]
					},
					{
						name: "峨眉悦山府（还有好东西",
						src: '../../static/image/tang.jpg',
						tag: [{
								name: '自助',
								type: 'primary'
							},
							{
								name: '文化',
								type: 'warning'
							}, {
								name: '新人',
								type: 'success'
							}
						]
					},
				],
				list: [
					'寒雨连江夜入吴',
					'平明送客楚山孤',
					'洛阳亲友如相问',
					'一片冰心在玉壶'
				]
			}
		},
		methods: {
			toPage(item){
				console.log(11)
				this.$tool.uniNavigateTo({
					url:"/pages/report/manage"
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.header{
		padding: 0 40rpx;
	}
	.flex-dropdown{
		left: 0;
		right: 0;
		top: 100rpx;
		z-index: 10;
	}
	
	.list {
		padding: 30rpx;
		.left {
			width: 200rpx;
			height: 200rpx;
			position: relative;
			margin-right: 20rpx;
			.img {
				width: 100%;
				height: 100%;
				border-radius: 4rpx;
			}

			.posi {
				position: absolute;
				top: 0;
				background: rgba(158, 191, 150, 1);
				color: #fff;
				padding: 6rpx 10rpx;
				font-size: 20rpx;
			}
		}

		.right {
			width: calc(100% - 220rpx);
			.title{
				margin-bottom: 10rpx;
				font-size: 30rpx;
				font-weight: 600;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
			.detail {
				font-size: 20rpx;
				margin-left: 10rpx;
				line-height: 1.6;
				color: #82848a;
			}

			.tags {
				display: flex;
				align-items: center;
				margin-bottom: 10rpx;
				
				.tag {
					&:not(:last-child){
						margin-right: 10rpx;
					}
				}
			}
		}
	}
</style>
