<template>
	<view class="wrap">
		<a-navbar title="房屋详情" @back="$tool.uniSwitchTab({url:'/pages/report/index'})"></a-navbar>
		<view class="banners" v-if="swiperList && swiperList.length">
			<u-swiper :list="swiperList" name="image" :mode="'dot'" :height="400"></u-swiper>
		</view>
		<view class="bg title">基本信息</view>
		<u-form class="form" v-if="form">
			<u-form-item class="bg" label-align="right" label="楼盘编号:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.ysbianhao"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="项目标题:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.xmbiaoti"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘状态:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.loupanzt"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="均价:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.junjia"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="总价:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.zongjia"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="优惠:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.youhui"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地铁站:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.ditiezhan"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="区域位置:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.quyu"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="新房顾问:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.weihurenid"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系电话:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.dianhua"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘地址:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.lpdizhi"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="售楼处地址:" label-width='170'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.slcdizhi"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="开发商:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.kaifashang"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="开盘时间:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.kaipansj"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="售卖资格:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.smzglx"></u-input>
			</u-form-item>
			
			<view class="detail">详情</view>
			<u-form-item class="bg" label-align="right" label="建筑类型:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.jianzhulx"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="占地面积:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.zdmianji"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="建筑面积:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.zjmianji"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="规划户数:" label-width='150'>
				<u-input type="text" :disabled="false"  placeholder='' v-model="form.guihuahs" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业类型:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.wuyelx" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="绿化率:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.lvhualv" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="容积率:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.rongjilv" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="产权年限:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.cqnx" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="项目特色:" label-width='150'>
				<u-input type="textarea" :disabled="true"  placeholder='' v-model="form.xmtese" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼盘户型:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.jushi" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业公司:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.wuyegongsi" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="物业费:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.wuyefei" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地上车位:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.dschewei" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="地下车位:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.dxchewei" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="车位配比:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.cwpeibi" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供水方式:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.gongshuifs" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供电方式:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.gongdianfs" ></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="供暖方式:" label-width='150'>
				<u-input type="text" :disabled="true"  placeholder='' v-model="form.gongnuanfs" ></u-input>
			</u-form-item>
			<view class="rim">周边规划 - 地铁</view>
			<view class="bg ta">
				<!-- <u-input class="ditie" type="textarea "  :disabled="true"  placeholder='' v-model="form.zbghdt" ></u-input> -->
				<view>{{ditie}}</view>
			</view>
			<view class='rim'>周边规划 - 购物</view>
			<view class="bg ta">
				<u-input  type="textarea" height='200' :disabled="true"  placeholder='' v-model="form.zbghgw" ></u-input>
			</view>
			<view class="rim">周边规划 - 公园</view>
			<view class="bg ta">
				<u-input type="textarea" height="200" :disabled="true"  placeholder='' v-model="form.zbghgy" ></u-input>
			</view>
			<view class="rim">周边规划 - 其他</view>
			<view class="bg ta">
				<u-input type="textarea" height="200" :disabled="true"  placeholder='' v-model="form.zbghqt" ></u-input>
			</view>
			<view class="rim">新房顾问</view>
			<view class="bg ta height" v-if="list && list.length">
				<view class="item" v-for="(x , index ) in list" :key='index'>
					<u-image class='inline' width='60px' height='60px'  :src='x.touxiang' shape='circle'></u-image>
					<view class="inline right">
						<view class="tit">{{x.name}}</view><!-- ({{x.dianhua}}) -->
						<!-- <view class='bott'>{{x.de}}</view> -->
					</view>
				</view>
			</view>
			<view class="rim">户型素材</view>
			<view class="bg bottom">
				<view class="inlinebox">
					<u-image @click='perview(index)' class='img' v-for='(x,index) in huxing' :key='index' :src='x.image' width='370' height='370'></u-image>
				</view>				
			</view>
			<view class="btn">
			<!-- 	<u-button :ripple="true" class="b" type='primary'><u-icon name='phone'></u-icon>电话咨询</u-button> -->
				<u-button :ripple="true" class='b' type='success' @click="keubaobei"><u-icon name='file-text'></u-icon>客户报备</u-button>
			</view>
		</u-form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				detail:null,
				form:null,
				value:"没有对应参数",
				list: [],
				swiperList: [],
				huxing:[]
			}
		},
		computed:{
			ditie(){
				let reg = /[a-z]/g
				return	this.form.zbghdt
			}
		},
		onLoad(options){
			options.id && this.getDetail(options.id)
		},
		methods:{
			perview(index){
				console.log(index)
				let urls = [];
				let arr = this.huxing ;
				for(let i= 0 ; i< arr.length ; i++){
					urls.push(arr[i].image)
				}
				uni.previewImage({
					current:index,
					urls:urls,
					success:function(e){
						console.log(e)
					},
					fail:function(e){
						console.log(e)
					}
				})
			},
			keubaobei(){
				uni.showToast({
					title:'稍后开放',
					icon:'none'
				})
			},
			getDetail(id){
				this.$tool.uniRequest({
					url:"xinfang/ysneirong/",
					method:'GET',
					params:{
						id
					},
					success:(res)=>{
						console.log(res)
						this.detail=res
						this.form=res.ysfang
						//this.form.xmtese=res.xmtese.map(item=>item.lxming).join(',')
						this.swiperList=res.topphoto
						this.list=res.xfguwens;
						this.huxing = res.ysphoto;
					}
				})
			},
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
		.ditie{
			width:100%;
			white-space: pre-wrap;
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
			margin-top:55px;
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
