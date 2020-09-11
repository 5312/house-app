<template>
	<view class="wrap">
		<view class="banners">
			<u-swiper :list="swiperList" :mode="'dot'" :height="400"></u-swiper>
		</view>
		<view class="bg title">基本信息</view>
		<u-form class="form">
			<u-form-item class="bg" label-align="right" label="楼盘编号:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="项目标题:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘状态:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="均价:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="总价:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="优惠:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地铁站:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="区域位置:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="新房顾问:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系电话:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘地址:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="售楼处地址:" label-width='170'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="开发商:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="开盘时间:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="售卖资格:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘地址:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<view class="detail">详情</view>
			<u-form-item class="bg" label-align="right" label="建筑类型:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="占地面积:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="建筑面积:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="规划户数:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业类型:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="绿化率:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="容积率:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="产权年限:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="项目特色:" label-width='150'>
				<u-input type="textarea" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘户型:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业公司:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业费:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地上车位:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地下车位:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="车位配比:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供水方式:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供电方式:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供暖方式:" label-width='150'>
				<u-input type="text" disabled="false" v-model="value" ></u-input>
			</u-form-item>
			<view class="rim">周边规划 - 地铁</view>
			<view class="bg ta">
				<u-input type="textarea " height="200" disabled="false" v-model="value" ></u-input>
			</view>
			<view class='rim'>周边规划 - 购物</view>
			<view class="bg ta">
				<u-input  type="textarea" height='200' disabled="false" v-model="value" ></u-input>
			</view>
			<view class="rim">周边规划 - 公园</view>
			<view class="bg ta">
				<u-input type="textarea" height="200" disabled="false" v-model="value" ></u-input>
			</view>
			<view class="rim">周边规划 - 其他</view>
			<view class="bg ta">
				<u-input type="textarea" height="200" disabled="false" v-model="value" ></u-input>
			</view>
			<view class="rim">新房顾问</view>
			<view class="bg ta height">
				<view class="item" v-for="(x , index ) in list" :key='index'>
					<u-image class='inline' width='60px' height='60px' shape='circle'></u-image>
					<view class="inline right">
						<view class="tit">{{x.name}}({{x.tel}})</view>
						<view class='bott'>{{x.de}}</view>
					</view>
				</view>
			</view>
			<view class="rim">户型素材</view>
			<view class="bg bottom">
				<view class="inlinebox">
					<u-image class='img' v-for='(x,index) in swiperList' :key='index' :src='x.image' width='370' height='370'></u-image>
				</view>
				<!-- <view class="inlinebox">
					<u-image class='img' v-for='(x,index) in swiperList' :key='index' :src='x.image' width='370' height='370'></u-image>
				</view> -->
			</view>
			<view class="btn">
				<u-button :ripple="true" class="b" type='primary'><u-icon name='phone'></u-icon>电话咨询</u-button>
				<u-button :ripple="true" class='b' type='success'><u-icon name='file-text'></u-icon>客户报备</u-button>
			</view>
		</u-form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: '暂无数据',
				list: [{
					name: '刘达明',
					tel: '1333333333',
					de:'Adaing'
				},{
					name: '刘达明',
					tel: '1333333333',
					de:'Adaing'
				},{
					name: '刘达明',
					tel: '1333333333',
					de:'Adaing'
				},{
					name: '刘达明',
					tel: '1333333333',
					de:'Adaing'
				}],
				swiperList: [{
						image: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					}
				],
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>
	$bg:#F2F2F2;
	.inline{
		display: inline-block;
		vertical-align: middle;
	}
	.height{
		height:480rpx;
		overflow: auto;
	}
	.bottom{
		margin-bottom: 100rpx;
	}
	.bg {
		background-color: #fff;
		.right{
			width:calc(100% - 150px);
			margin-left: 20rpx;
			.tit{
				font-size: 32rpx;
				font-weight: 600;
			}
			.bott{
				font-size:20rpx;
			}
		}
		.item{
			margin:20rpx;
		}
		.inlinebox{
			height:370rpx;
			white-space: nowrap;
			overflow-x:auto ;
			.img{
				display: inline-block;
				vertical-align: middle;
				padding:20rpx;
			}
		}
	}

	.wrap {
		background-color: $bg;

		.banners {
			padding: 20rpx;
			position: fixed;
			top:0;
			width:100%;
			z-index: 9;
			background-color: $bg;
			/* height:400rpx; */
		}

		.title {
			padding: 20rpx;
			background: #fff;
			margin-bottom: 20rpx;
			color:#999999;
			margin-top:450rpx;
		}

		.detail {
			@extend .title;
			margin: 10rpx 0;
		}

		.rim {
			@extend .detail;
		}
		.ta{
			padding:20rpx;
		}
	}
	.btn{
		position: fixed;
		bottom: 0rpx;
		z-index: 9;
		width: 100%;
		text-align: center;
		background: #fff;
		.b{
			width:35%;
			display: inline-block;
			margin:10rpx 20rpx ;
		}
	}
</style>
