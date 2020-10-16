<template>
	<view class="reimbursement page-bg min-height-100">
		<a-navbar title="报单管理" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-subsection active-color="#19be6b" :list="list" :current="currentIndex" @change='tagChange'></u-subsection>
			<view class="card-wrap" v-if="cardList && cardList.length">
				<view class="card bg-white border-radius-8" v-for="(item,index) in cardList" :key='index' @click="toDetail(item)">
					<u-form ref="uForm" label-width='140' :border-bottom='false'>
						<u-form-item>
							{{item.dizhi}}
						</u-form-item>
						<u-form-item label="业主姓名:">
							{{item.yzname}}
						</u-form-item>
						<u-form-item label="客户姓名:">
							{{item.khname}}
						</u-form-item>
						<u-form-item label="成交总价:">
							{{item.cjjiage}}
						</u-form-item>
						<u-form-item label="分成业绩:">
							{{item.ysyongjin}}
						</u-form-item>
						<u-form-item label="交易归属:">
							{{item.bumen}}
						</u-form-item>
						<u-form-item label="交易类型:">
							{{item.typename}}
						</u-form-item>
					</u-form>
					<u-steps :list="numList" active-color="#19be6b" :current="item.jindu" mode="number"></u-steps>
				</view>
			</view>
		</view>
		<view class="radius fixed add-icon flex a-center j-center" @click="showTa=true">
			<u-icon name="plus" color="#ffffff" size="40"></u-icon>
		</view>
		<u-popup v-model="showTa" mode="center" border-radius='14' close-icon-color='#fff' :closeable='true'>
			<view >
				<view class="tit">选择报单类型</view>
				<view class="showTa">
					<view class="li" v-for="(item,index) in list" :key='index'
						@click="add(item)">类型{{index+1}}：{{item.name}}</view>
				</view>
			</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				currentIndex: 0,
				showTa:false,
				pageNum: 1,
				total: 0,
				numList: [{
					name: '店长审核'
				}, {
					name: '区长审核'
				}, {
					name: '后台审核'
				}, {
					name: '结案'
				}, ],
				cardList: null,
				list: [{
						name: '售单'
					},
					{
						name: '租单'
					}, {
						name: '新盘'
					}
				],
			}
		},
		onPullDownRefresh(){
			console.log("下拉了")
			this.pageNum = 1
			this.getList()
		},
		onReachBottom(){
			console.log("触底了")	
			if(this.cardList.length<this.total){
				this.pageNum +=1
				this.getList(true)
			}
		},
		onLoad(option) {
			if(option.type){
				this.currentIndex = option.type - 1 || 0;
				console.log(this.currentIndex)
			}
			
			this.init()
		},
		methods: {
			init() {
				this.getList()
			},
			tagChange(val) {
				this.currentIndex = val
				this.pageNum = 1
				this.getList()
			},
			getList(isReachBottom=false) {
				this.$tool.uniRequest({
					url: "chengjiao/",
					method: 'GET',
					params: {
						p: this.pageNum,
						type: (this.currentIndex + 1)
					},
					success: (res) => {
						let result = res.list || []
						if(isReachBottom){
							this.cardList=[...this.cardList,...result]
						}else{
							this.cardList = result
						}
						this.total = parseInt(res.totalcount)
					}
				})
			},
			toDetail(item){
				this.$tool.uniNavigateTo({
					url: `/pages/home/reimbursement/detail?id=${item.id}&curr=${this.currentIndex}`
				})
			},
			add(item) {
				this.showTa=false
				this.$tool.uniNavigateTo({
					url: `/pages/home/reimbursement/add?type=${item.name}`
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.tit{
		text-align: center;
		line-height: 2;
		padding: 20rpx 0 10rpx 0;
		color: white;
		background:rgb(7, 193, 96);
	}
	.showTa{
		text-align: center;
		width: 400rpx;
		.li{
			border-top: 1px solid #ddd;
			padding:30rpx;
			&:active{
				color:rgb(7, 193, 96);
			}
			&:hover{
				color:rgb(7, 193, 96);
			}
		}
	}
	.reimbursement {
		
		.add-icon {
			width: 100rpx;
			height: 100rpx;
			background: #19be6b;
			right: 60rpx;
			bottom: 60rpx;
			z-index: 400;

			&:active {
				opacity: 0.8;
			}
		}

		.content {
			padding-top: 40rpx;

			.card-wrap {
				margin-top: 40rpx;
				padding-bottom: 20rpx;

				.card {
					margin: 0rpx 40rpx 40rpx 40rpx;
					padding: 20rpx 40rpx;

					/deep/.u-form-item {
						padding: 0;
					}
				}
			}
		}
	}
</style>
