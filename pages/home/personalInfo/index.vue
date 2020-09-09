<template>
	<view class="personal-info min-height-100 page-bg">
		<a-navbar title="个人资料" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="header tabbar-bg flex a-center j-center flex-column">
			<u-avatar src='http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg' size="120"></u-avatar>
			<view class="name text-white">Ace</view>
		</view>
		<view class="content">
			<template v-for="(item,index) in list">
				<!-- <a-section :item='item' @selectClick='select'></a-section> -->
				<view class="a-section height-line-80 flex a-center j-between flex-row bg-white" @click="select(item)">
					<view class="left flex a-center j-start flex-row">
						<u-icon :name="item.icon" color="#c8c9cc" class="icon" size="36" v-if="item.icon"></u-icon>
						<text>{{item.name}}</text>
					</view>
					<view class="right flex a-center j-end flex-row">
						<text class="text-grey">{{item.info}}</text>
						<u-icon name="arrow-right" color="#c8c9cc" class="margin-l-10"></u-icon>
					</view>
				</view>
				<u-gap height="10" v-if="index !=(list.length-1)"></u-gap>
			</template>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				val:0,
				list:[
					{
						name:"基本信息",
						info:"完善",
						path:"/pages/home/personalInfo/baseInfo",
						icon:'man-add-fill'
					},{
						name:"入职信息",
						info:"待完善",
						path:"/pages/home/personalInfo/entryInfo",
						icon:'tags-fill'
					},{
						name:"联系信息",
						info:"完善",
						path:"/pages/home/personalInfo/contactInfo",
						icon:'phone-fill'
					},
				]
			}
		},
		methods:{
			select(item){
				this.$tool.uniRedirectTo({
					url:item.path
				})
			}
		}
	}
</script>

<style scoped lang="scss">
.personal-info{
	.header{
		height: 240rpx;
		.name{
			margin-top: 14rpx;
			font-size: 30rpx;
		}
	}
	.content{
		.line{
			height: 80rpx;
			padding: 0 20rpx;
		}
	}
}
</style>
