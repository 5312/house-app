<template>
	<view>
		<u-tabs :list="lists" active-color='#07c160' inactive-color='#000' :is-scroll="false" :current="current" @change="change"></u-tabs>

		<view class="wrap">

			<view class="title">第一步:基本信息</view>
			<view class="main">
				<u-form class="form" :model="form" ref="uForm">
					<!-- <u-form-item class="bg" label-width='150' label-align='rigth' label="报单类型">
					<u-input v-model="form.leixing" />
				</u-form-item> -->
					<u-form-item class="bg" label-width='150' label-align='rigth' label="房屋地址">
						<u-input v-model="form.fdizhi" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="房屋类型">
						<u-input :selectOpen="fangType" type="select" v-model="form.fdizhi" @click='fangType = true' />
						<u-select v-model="fangType" :list="list"></u-select>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="所属小区">
						<u-input :selectOpen="xiaoqu" type="select" v-model="form.xiaoqu" @click='xiaoqu = true' />
						<u-select v-model="xiaoqu" :list='list'></u-select>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="使用面积">
						<u-input v-model="form.symianji" />
					</u-form-item>
					<u-form-item class="bg " label-width='150' label-align='rigth' label="门牌号">
						<!-- <view class="label inline">门牌号</view> -->
						<view class="inline">
							<view class="flex  input">
								<u-input class="item" border='true' v-model="form.zuodong" placeholder='栋' :type="type" />
								<u-input class="item" border='true' v-model="form.danyuan" placeholder='单元' :type="type" />
								<u-input class="item" border='true' v-model="form.fanghao" placeholder='房号' :type="type" />
							</view>
						</view>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="户型">
						<view class="inline">
							<view class="flex  input">
								<u-input class="item2" border='true' v-model="form.shi" placeholder='室' :type="type" />
								<u-input class="item2" border='true' v-model="form.ting" placeholder='厅' :type="type" />
								<u-input class="item2" border='true' v-model="form.wei" placeholder='卫' :type="type" />
								<u-input class="item2" border='true' v-model="form.yangtai" placeholder='阳台' :type="type" />
							</view>
						</view>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主姓名">
						<u-input v-model="form.yezhu" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主电话">
						<u-input v-model="form.yezhudianhua" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主性别">
						<u-radio-group class="bg  flex" v-model="yezhulx">
							<u-radio v-for="(item, index) in list" :key="index" :name="item.name" :disabled="item.disabled">
								{{item.name}}
							</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户姓名">
						<u-input v-model="form.khxingming" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户电话">
						<u-input v-model="form.dianhua" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户性别">
						<u-radio-group class="bg  flex" v-model="kehulx">
							<!-- <label for="">客户性别</label> -->
							<u-radio v-for="(item, index) in list" :key="index" :name="item.name" :disabled="item.disabled">
								{{item.name}}
							</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="成交时间">
						<u-input :selectOpen="show" v-model="form.cjtime" type="select" @click='show = true' />
						<u-calendar v-model="show" :mode="date"></u-calendar>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="成交价格">
						<u-input v-model="form.cjjiage" placeholder='xxx元' />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth'  label="成交业绩">
						<u-input v-model="form.ysyongjin" placeholder='xxx元' />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="备注">
						<u-input type='textarea' v-model="form.beizhu" placeholder="" />
					</u-form-item>
					<!-- liucneg -->
					<view class="steps bg margin-bottom margin-top">
						<view class="small-title">审批流程</view>
						<u-steps direction='column' mode="dot" :list="numList" :current="current"></u-steps>
					</view>
					<view class="btn">
						<button class="bt" @click="submit">提交</button>
					</view>
				</u-form>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				fangType:false,
				xiaoqu:false,
				show:false,
				form: {
					leixing: null,
					fwleixing: null,
					fdizhi: null,
					xiaoqu: '',
					symianji: '',
					zuodong: '',
					fanghao: '',
					danyuan: '',
					shi: '',
					ting: '',
					wei: '',
					yangtai: '',
					yezhu: '',
					yezhudianhua: '',
					yezhulx: '',
					khxingming: '',
					dianhua: '',
					kehulx: '',
					cjtime: '',
					cjjiage: '',
					ysyongjin: '',
					beizhu: ''
				},
				lists: [{
						name: '发起提交'
					},
					{
						name: '查看数据'
					}
				],
				current: 0,
				list: [{
						name: '先生',
						disabled: false
					},
					{
						name: '女士',
						disabled: false
					},
				],
				type: 'text',
				numList: [{
						name: '店长审核'
					},
					{
						name: '区长审核'
					},
					{
						name: '后台审核'
					},
					{
						name: '结案'
					}
				],

			}
		},
		methods: {
			change(index) {
				this.current = index;
			},

		}
	}
</script>

<style lang="scss" scoped>
	* {
		font-size: 30rpx;
	}
	.form {
		border-radius: 20rpx;
		overflow: hidden;
	}

	.wrap {
		padding: 20rpx;
		background: #F8F8F8;

		.title {
			font-size: 32rpx;
			color: #767676;
		}

		.main {
			background-color: #F8F8F8;
			margin: auto;
			padding-top: 30rpx;

			.flex {
				display: flex;
				justify-content: space-between;
				align-items: center;
			}

			.radio {
				width: 100%;
				padding: 30rpx;
			}

			.bg {
				background: #fff;

				.fx {
					display: flex;
					padding: 20rpx 10rpx;

				}

				.inline {
				/* 	width: calc(100% - 70px); */
					/* margin: auto; */ 
					.input {
						padding-left: 10rpx;
						justify-content: space-around;
						
						.item {
							width: 25%;
							flex: 0 0 auto;
						}
						.item2{
							width: 21%;
							margin:0 10rpx;
							flex: 0 0 auto;
						}
					}
				}

				.label {
					width: 70px;
					text-align: right;
				}

				.textarea {
					padding: 20rpx 30rpx;
				}
			}

			.red {
				color: red;
			}

			.margin-top {
				margin-top: 30rpx;
			}

			.margin-bottom {
				margin-bottom: 30rpx;
			}

			.steps {
				padding: 30rpx;

				.small-title {}
			}

			.btn {
				width: 80%;
				margin: 20rpx auto;

				.bt {
					background-color: #07c160;
					color: #fff;
				}
			}
		}
	}
</style>
