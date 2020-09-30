<template>
	<view class="body">
		<view class="nav">
			<a-navbar title="佣金代收" @back="$tool.uniSwitchTab({url:'/pages/home/index'})" background-color='#fff'></a-navbar>
		</view>
		<view class="content" v-for="(x,y) in list" :key='y'>
			<view class="content-bg">
				<view class="title flex a-center j-between">
					<view>佣金记录</view>
					<view class="corsur" @click="trash(y)">
						<u-icon name="trash"></u-icon>
					</view>
				</view>
				<!-- 费用类型 -->
				<view class="hlab">费用类型</view>
				<u-input v-model="x.kostenType" @click='project = true;nowClick=y'></u-input>
				<!-- 费用项目 -->
				<view class="hlab">费用项目</view>
				<view class="row">
					<u-input v-model="x.kostenProject" @click='project = true;nowClick=y'></u-input>
					<u-select @confirm="confirm" mode="mutil-column-auto" v-model="project" :list="kostenList"></u-select>
				</view>
				<view class="hlab">交费人</view>
				<view class="row">
					<u-input v-model="x.people" @click='project == true'></u-input>
				</view>
				<view class="hlab">金额</view>
				<view class="row">
					<u-input v-model="x.money" @click='project == true'></u-input>
				</view>
				<view class="hlab">预计缴费日期</view>
				<view class="row">
					<u-input v-model="x.date" @click='project == true'></u-input>
				</view>
				<view class="hlab">备注</view>
				<view class="row">
					<u-input v-model="x.rank" @click='project == true'></u-input>
				</view>
			</view>
		</view>
		<view class="bottom fixed flex a-center j-center">
			<u-toast ref="uToast" />
			<u-button type="primary" @click="addRec">添加佣金记录</u-button>
			<u-button type="success">保存</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				nowClick: '', //当前点击
				list: [

				],
				project: false, //列选择器
				kostenList: [{
						value: 1,
						label: '金融类',
						children: [{
								value: 2,
								label: '广东',

							},
							{
								value: 5,
								label: '广西',

							}
						]
					},
					{
						value: 8,
						label: '房产类',
						children: [{
							value: 9,
							label: '纽约',

						}]
					}
				], //费用项目
			}
		},
		onLoad() {
			this.addRec();//先添加一条
		},
		methods: {
			trash(index) { //删除列表
				if (this.list.length > 1) {
					this.list.splice(index, 1)
				} else {
					this.$refs.uToast.show({
						title: '这是最后一个了哦',
						type: 'warning',
						//url: '/pages/user/index'
					})
				}

			},
			addRec() { //添加记录
				this.list.push({
					kostenType: '金融类',
					kostenProject: '',
					people: '',
					money:'',
					date:'',
					rank:'',
				})
			},
			// 注意返回值为一个数组，单列时取数组的第一个元素即可(只有一个元素)
			confirm(e) {
				let index = this.nowClick;
				this.list[index].kostenType = e[0].label;
				this.list[index].kostenProject = e[1].label;
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
		.body {
			height: 100%;
			background: $my-bg;

			.nav {
				margin-bottom: 20rpx;
			}

			.content {
				background: $my-bg;
				margin:50rpx auto;
				padding:20rpx;
				.content-bg {
					background-color: #fff;
					border-radius: 16rpx;
					width: 80%;
					margin: auto;
					padding: 20rpx;
				}

				.title {
					margin-bottom: 20rpx;
				}

				.hlab {
					font-weight: 700;
					font-size: 32rpx;
					padding: 10rpx 0;
				}
			}
			&:last-child{
				margin-bottom: 110rpx;
			}
			.bottom {
				bottom: 0;
				width: 100%;
				padding: 10rpx;
				background: #fff;
				z-index: 99;
			}
		}
	}
</style>
