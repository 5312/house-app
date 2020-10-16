<template>
	<view class="report relative">
		<u-navbar :is-back="false" :background="{backgroundColor: '#EDEDED'}" :height='50'>
			<view class="slot-wrap header flex a-center j-start flex-row">
				<u-search placeholder="请输入搜索内容" v-model="searchVal" :clearabled="true" bg-color="#fff" :show-action="false" @search='init'></u-search>
				<u-icon name="search" color="#ffffff" size="56" class="margin-l-30" @click="init"></u-icon>
			</view>
		</u-navbar>
		<view class="content">
			<view>
				<u-dropdown-list :dropdownList='dropdownList' @change='dropdownChange'></u-dropdown-list>
				<u-notice-bar mode="horizontal" :list="list"></u-notice-bar>
			</view>
			<view class="list-wrap" v-if="shop">
				<view v-for="(x,y) in shop" class="list border-bottom flex a-start j-start flex-row" @click="toPage(x)">
					<view class="left flex-shrink">
						<image :src="x.img" class="img"></image>
						<view class="posi" v-if="x.imgtage">
							{{x.imgtage}}
						</view>
					</view>
					<view class="right">
						<view class="title">{{x.xmbiaoti}}</view>
						<view class='tags'>
							<text v-for="item in x.tage" class="tag" :style="{background:item.yanse}">{{item.name}}</text>
						</view>
						<view class="detail">
							<view>编号：{{x.ysbianhao}}</view>
							<view>均价：{{x.junjia}}</view>
							<view>产权年限(年)：{{x.cqnx}}</view>
						</view>
						<view></view>
					</view>
				</view>
			</view>
			<view v-if="!shop" class="empty">
				<u-empty  text="数据为空" mode="data"></u-empty>
			</view>
		</view>
	</view>

</template>

<script>
	import api from '@/utils/api/info.js'
	export default {
		data() {
			return {
				pageNum: 1, //分页
				total: null, //总数
				intPage: null, //当前
				searchVal: '',
				dropdownList: [{
						show: false,
						options: [{
							id: 0,
							text: '全部区域',
							value: '',
							type: 'area',
							select: false
						}]
					},
					{
						show: false,
						options: [{
							id: 0,
							text: '全部类型',
							value: '',
							type: 'type',
							select: false
						}]
					},
					{
						show: false,
						options: [{
							id: 0,
							text: '全部状态',
							value: '',
							type: 'status',
							select: false
						}]
					}
				],
				shop: [],
				count: 0,
				list: [
					'暂时没有推送消息'
				],
				param: null,
				isPush:false,
			}
		},
		onLoad() {
			this.init()
		},
		onPullDownRefresh() {
			console.log("下拉了")
		},
		onReachBottom() {
			this.isPush = true;
			if (this.intPage < this.total) {
				this.pageNum += 1
				this.getList()
			}else{
				this.isPush = false;
			}
		},
		methods: {
			init() {
				this.getList()
			},
			selectParams() {
				let a ={
					0:'',
					1:'',
					2:''
				}; //['quyu','wuyelx','xlpzt']
				this.dropdownList.forEach((item, i) => {
					if (item.options && item.options.length > 0) {
						item.options.forEach(val => {
							if (val.select) {
								a[i]= i==0?val.id : val.lxid
							}
						})
					}
				})
				this.param = [a[0], a[1], a[2]]
			},
			getList() {
				this.selectParams();
				this.isSelectPush()
				const params = {
					quyu: this.param[0],
					wuyelx: this.param[1],
					xlpzt: this.param[2],
					sousuo: this.searchVal,
					p: this.pageNum, //分页
				};
				
				api.xinFang(params).then(res => {
					this.intPage = res.intPage; //当前页
					this.total = res.totalpage; //总页数
					if(this.isSelectPush() ||  !this.searchVal){
						console.log('触底加载')
						this.shop.push(...res.ysfang || []);
					}else{
						console.log('筛选加载')
						this.shop = res.ysfang;
					}
					console.log(params.p,this.shop.length);
					this.isPush = false;
					this.count += 1
					if (this.count > 1) return
					this.select(res);
				})
			},
			isSelectPush(){
				if(this.isPush){
					return true;//触底
				}
				this.pageNum = 1;
				return false;
			},
			select(res) {
				this.dropdownList = [{
						show: false,
						options: [{
							id: 0,
							text: '全部区域',
							value: '',
							type: 'area',
							select: false
						}]
					},
					{
						show: false,
						options: [{
							id: 0,
							text: '全部类型',
							value: '',
							type: 'type',
							select: false
						}]
					},
					{
						show: false,
						options: [{
							id: 0,
							text: '全部状态',
							value: '',
							type: 'status',
							select: false
						}]
					}
				]
				res.quyu.forEach(item => {
					let obj = item
					obj.text = item.xzqming
					obj.value = item.id
					obj.select = false
					obj.type = 'area'
					this.dropdownList[0].options.push(obj)
				})
				res.xlpzt.forEach(item => {
					let obj = item
					obj.text = item.lxming
					obj.value = item.id
					obj.select = false
					obj.type = 'status'
					this.dropdownList[2].options.push(obj)
				})
				res.wuyelx.forEach(item => {
					let obj = item
					obj.text = item.lxming
					obj.value = item.id
					obj.select = false
					obj.type = 'type'
					this.dropdownList[1].options.push(obj)
				})
			},
			toPage(item) {
				this.$tool.uniNavigateTo({
					url: `/pages/report/detail?id=${item.id}`
				})
			},
			dropdownChange(value, option) {
				this.getList()
			}
		}
	}
</script>

<style lang="scss" scoped>
	.header {
		padding: 0 40rpx;
	}

	* {
		font-size: 32rpx;
	}
	.empty{
		margin-top:40rpx;
	}
	.flex-dropdown {
		left: 0;
		right: 0;
		top: 100rpx;
		z-index: 10;
		font-size: 40rpx !important;
	}

	.list {
		padding: 30rpx;

		.left {
			width: 300rpx;
			height: 260rpx;
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

			.title {
				margin-bottom: 10rpx;
				font-size: 40rpx;
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
					display: inline-block;
					padding: 4rpx 6rpx;
					font-size: 30rpx;
					color: white;
					border-radius: 4rpx;
					margin-bottom: 6rpx;

					&:not(:last-child) {
						margin-right: 10rpx;
					}
				}
			}
		}
	}
</style>
