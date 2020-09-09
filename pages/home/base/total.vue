<template>
	<view class="total page-bg min-height-100">
		<a-navbar :title="navbarTitle" @back="back"></a-navbar>
		<view class="content">
			<view class="line-1 bg-white flex a-center j-between flex-row padding-lr-40 height-line-80">
				<view>
					当前总业绩：<text class="text-blue">0</text>
				</view>
				<view>
					已收业绩：<text class="text-blue">0</text>
				</view>
			</view>
			<view class="line bg-white flex a-center j-center flex-row padding-lr-40 height-line-80 border-bottom" @click="isShowPicker=true">
				<text class="inline-block">{{currentTime}}</text>
				<u-icon :name="isShowPicker?'arrow-up-fill':'arrow-down-fill'" class="margin-l-10" color="#c8c9cc" ></u-icon>
			</view>
			<u-collapse>
				<u-collapse-item :title="item.head" v-for="(item, index) in itemList" :key="index" class="bg-white border-bottom">
					<view class="page-bg"  v-for="(val,i) in item.children" :key='i'>
						<view class="flex a-center j-between flex-row padding-lr-40 height-line-80 border-bottom">
							<text>当月总业绩</text>
							<text class="text-blue">￥:{{val.value1}}</text>
						</view>
						<view class="flex a-center j-between flex-row padding-lr-40 height-line-80 border-bottom">
							<text>当月已收业绩</text>
							<text class="text-blue">￥:{{val.value2}}</text>
						</view>
					</view>
				</u-collapse-item>
			</u-collapse>
		</view>
		<u-picker v-model="isShowPicker" mode="time" :params='params' @confirm='confirm'></u-picker>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				currentTime:'2019',
				params:{
					year: true,
					month: false,
					day: false,
					hour: false,
					minute: false,
					second: false,
					timestamp: false
				},
				isShowPicker:false,
				itemList: [{
					head: "2020-01",	
					open: true,
					disabled: true,
					navbarTitle:'',
					pageType:'',
					children:[
						{
							value1:"43212205.5",
							value2:"4305.5"
						}
					]
				},{
					head: "2020-01",	
					open: true,
					disabled: true,
					children:[
						{
							value1:"43212205.5",
							value2:"4305.5"
						}
					]
				}],
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.init()
		},
		methods:{
			init(){
				switch(this.pageType){
					case "shop":
						this.navbarTitle="门店总业绩"
						break
					case "shopGroup":
						this.navbarTitle="店组总业绩"
						break
					case "person":
						this.navbarTitle="个人总业绩"
						break
				}
			},
			confirm(val){
				this.currentTime=val.year
			},
			back(){
				this.$tool.uniRedirectTo({
					url:`/pages/home/base/index?type=${this.pageType}`,
				})
			},
		}
	}
</script>

<style scoped lang="scss">
	.total {
		.content {
			.line-1 {
				margin-bottom: 40rpx;
			}
			/deep/.u-collapse-head{
				padding-left: 30rpx;
				padding-right: 30rpx;
			}
		}
	}
</style>
