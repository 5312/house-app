<template>
	<view class="reimbursement page-bg min-height-100">
		<a-navbar title="报账管理" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-subsection :list="list" :current="1"></u-subsection>
			<view class="card-wrap">
				<view class="card bg-white border-radius-8" v-for="(item,index) in cardList" :key='index'>
					<u-form ref="uForm" label-width='140' :border-bottom='false'>
						<u-form-item>
							{{item.address}}
						</u-form-item>
						<u-form-item label="业主姓名:">
							{{item.name1}}
						</u-form-item>
						<u-form-item label="客户姓名:">
							{{item.name2}}
						</u-form-item>
						<u-form-item label="成交总价:">
							{{item.value1}}
						</u-form-item>
						<u-form-item label="分成业绩:">
							{{item.value2}}
						</u-form-item>
						<u-form-item label="交易归属:">
							{{item.ace}}
						</u-form-item>
						<u-form-item label="交易类型:">
							{{item.type}}
						</u-form-item>
					</u-form>
					<u-steps :list="numList" :current="item.step" mode="number"></u-steps>
				</view>
			</view>
		</view>
		<view class="radius fixed add-icon flex a-center j-center" @click="add">
			<u-icon name="plus" color="#ffffff" size="40"></u-icon>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				numList: [{
					name: '店长审核'
				}, {
					name: '区长审核'
				}, {
					name: '后台审核'
				}, {
					name: '结案'
				}, ],
				cardList: [{
					address: "中梁海景房1-栋0884室内",
					name1: "小二",
					name2: "小三",
					value1: "99万",
					value2: "2000万",
					ace: "xxx-xxx-xxx",
					type: '卖单',
					step:1
				},{
					address: "中梁海景房1-栋0884室内",
					name1: "小二",
					name2: "小三",
					value1: "99万",
					value2: "2000万",
					ace: "xxx-xxx-xxx",
					type: '卖单',
					step:2
				}],
				list: [{
						name: '租单'
					},
					{
						name: '卖单'
					}, {
						name: '新盘'
					}
				],
			}
		},
		methods:{
			add(){
				this.$tool.uniNavigateTo({
					url:"/pages/home/reimbursement/detail"
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.reimbursement {
		.add-icon{
			width: 100rpx;
			height: 100rpx;
			background: #c8c9cc;
			right: 60rpx;
			bottom: 60rpx;
			z-index: 400;
			&:active{
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
