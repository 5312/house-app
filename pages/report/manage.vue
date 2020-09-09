<template>
	<view class="manage">
		<u-navbar :is-back="true" :background="{backgroundColor: '#EDEDED'}" :height='50'  back-icon-color='#000' title-color='#000'
			:custom-back="back">
			<view class="slot-wrap">
				<slot>
					<u-search shape="round" :action-style="{color:'#000'}" bg-color="#fff"></u-search>
				</slot>
			</view>
		</u-navbar>
		<view class="content">
			<u-tabs :list="list" bar-width="100" :is-scroll="false" :current="current" @change="change" class="border-bottom"></u-tabs>
			<view class="nav-wrap relative">
				<u-dropdown-list :dropdownList='dropdownList' ></u-dropdown-list>
				<u-button size="mini" type="primary" class="absolute btn" :style="{zIndex:dropdownList[0].show?1:99}">全部</u-button>
			</view>
			<view class="no-data flex a-center j-center" v-if="isNoDate">
				<u-empty text="数据为空" mode="list"></u-empty>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isNoDate:true,
				dropdownList:[
					{ show: false, options: [
						{ id: 0, text: '筛选', value: '', select: false },
						{ id: 1, text: '全场券', value: 1, select: false },
						{ id: 2, text: '品类券', value: 2, select: false },
						{ id: 3, text: '单品券', value: 3, select: false },
						{ id: 4, text: '业务券', value: 4, select: false }
					] }
				],
				background: {
					backgroundColor: 'rgba(158, 191, 150, 1)',
				},
				list: [{
					name: '我的报备(0)'
				}, {
					name: '我的到访(0)'
				}],
				current: 0,
				value1: 1,

				options1: [{
						label: '默认排序',
						value: 1,
					},
					{
						label: '距离优先',
						value: 2,
					},
					{
						label: '价格优先',
						value: 3,
					}
				],

			};
		},
		methods: {
			change(index) {
				this.current = index;
			},
			back(){
				this.$tool.uniSwitchTab({url:'/pages/report/index'})
			}
		}

	};
</script>

<style scoped lang="scss">
	.content{
		.no-data{
			height: calc(100vh - 400rpx);
		}
		.nav-wrap{
			width: 100%;
			/deep/.drop-item{
				justify-content: flex-start;
				padding: 0 40rpx;
			}
			.btn{
				right: 40rpx;
				position: absolute;
				margin-top: -72rpx;
				z-index: 99;
			}
		}
	}
	.slot-wrap {
		display: flex;
		align-items: center;
		flex: 1; 
		padding: 0 30rpx;
		color: white;
	}
</style>
