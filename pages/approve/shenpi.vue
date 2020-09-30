<template>
	<view class="body">
		<a-navbar title="审批" @back="$tool.uniSwitchTab({url:'/pages/home/index'})" background-color='#fff'></a-navbar>
		<u-tabs active-color="#00ca62" inactive-color="#000" :current='current' @change="change" bar-width="150" :list="list"
		 :is-scroll="false"></u-tabs>
		<u-empty v-if="detailList.length == 0" text="数据为空" class="empty" mode="list"></u-empty>
		<view v-if='current == 0 && detailList.length>0' class="wrap">
			<view v-for="(x,y) in detailList" :key='y' class="border-radius-8">
				<view class="detail" @click="xiangqing(x.id)">
					<view class="title">{{ x.to_title }}</view>
					<view class="time addtime">{{ x.addtime }}</view>
					<!-- 	<view class="time" v-html="x.neirong"></view> -->
					<u-parse class="time" :html="x.neirong" :tag-style='style'></u-parse>
					<view class="bottom">
						<view class="left">
							<u-icon name='home-fill' class="ic"></u-icon>{{x.bumen}}
						</view>
						<u-tag v-if="x.sh_zhuangtai == 0" text="店长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 1" text="区长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 2" text="后台审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 3" text="审批通过" type="info" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == '-1'" text="审批拒绝" type="error" shape="circle"></u-tag>
					</view>
				</view>
				<view class="hi"></view>
			</view>
		</view>
		<view v-else-if="current == 1  && detailList.length>0" class="wrap">
			<view v-for="(x,y) in detailList" :key='y'>
				<view class="detail" @click="xiangqing(x.id)">
					<view class="title">{{ x.to_title }}</view>
					<view class="time addtime">{{ x.addtime }}</view>
					<u-parse class="time" :html="x.neirong" :tag-style='style'></u-parse>
					<view class="bottom">
						<view class="left">
							<u-icon name='home-fill' class="ic"></u-icon>{{x.bumen}}
						</view>
						<u-tag v-if="x.sh_zhuangtai == 0" text="店长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 1" text="区长审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 2" text="后台审核" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == 3" text="审批通过" type="success" shape="circle"></u-tag>
						<u-tag v-if="x.sh_zhuangtai == '-1'" text="审批拒绝" type="error" shape="circle"></u-tag>
					</view>
				</view>
				<view class="hi"></view>
			</view>
		</view>

	</view>
</template>

<script>
	import api from '@/utils/api/info.js'
	export default {
		data() {
			return {
				current: 0,
				detailList: [],
				totalcount: '',
				totalpage: '',
				intPage: 1,
				list: [{
					name: '我的审批'
				}, {
					name: '我提交的审批'
				}],
				style: {
					span: ''
				}
			}
		},
		watch: {
			current() {
				//切换就清空列表
				this.detailList = [];
			}
		},
		onLoad(option) {
			this.current = option.current || 0;
			this.request() //审批列表
		},
		onReachBottom() {
			//触底一次页码加一
			if (this.intPage < this.totalpage) {
				this.intPage++;
				this.request();
			}
		}, 
		methods: {
			change(index) {
				this.current = index;
				this.request()
			},
			request() {
				let _this = this;
				let data = {
					type: this.current, //0我的审批1我提交的审批
					p: _this.intPage //页码
				};
				api.ywsp(data).then(res => {
					let arr = res.list;
					for (let i = 0; i < res.list.length; i++) {
						let q = arr[i].neirong.split(',').join(': <span>'); //加开头
						let h = q.split('#').slice(0, 2)
						let ht = h.join('</span></br>');
						console.log(h);
						arr[i].neirong = ht
					}
					_this.detailList.push(...arr);
					_this.totalcount = res.totalcount //总共条数
					_this.totalpage = res.totalpage //总共页数
					_this.intPage = res.intPage //当前页码
				});
			},
			xiangqing(id) {
				//跳转详情
				this.$tool.uniNavigateTo({
					url: `/pages/approve/shenpiDdetail?id=${id}&current=${this.current}`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		/* background-color:  #EDEDED; */
		height: 100%;

		.body {
			height: 100%;
			background-color: $my-bg;
			.wrap {
				background-color: $my-bg;
				padding: 40rpx;
				/* background: #fff; */
				padding-top: 10rpx;
			}
		}

	}

	.empty {
		position: relative;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		z-index: 9;
		margin: auto;
	}

	.hi {
		height: 20rpx;
		background: #EDEDED;
		width: 200%;
	}

	.wrap {


		.detail {
			line-height: 40rpx;
			border-radius: 10rpx;
			overflow: hidden;
			padding: 40rpx;
			padding-bottom: 10rpx;
			background: #fff;
			list-style: none;

			.time {
				font-size: 28rpx;
				color: #828282;
				line-height: 50rpx;
			}

			.addtime {
				color: #b9b9b9;
			}
		}

		.title {
			font-size: 40rpx;
			font-weight: bold;
			margin-bottom: 10rpx;
		}

		.bottom {
			text-align: right;
			padding: 10rpx;
			border-top: 1px solid #ddd;
			padding-top: 20rpx;
			display: flex;
			justify-content: space-between;

			.left {
				font-size: 28rpx;
			}

			.ic {
				margin-right: 20rpx;
			}

			.right {
				color: #D8A066;
			}
		}
	}
</style>
