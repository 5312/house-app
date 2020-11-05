<template>
	<view class="wrap">
		<a-navbar title="修改头像" @back="$tool.uniSwitchTab({ url: '/pages/home/index' })"></a-navbar>
		<view class="u-avatar-wrap"><image class="u-avatar-demo" :src="avatar" mode="aspectFill"></image></view>
		<u-button @tap="chooseAvatar">点击更换头像</u-button>
	</view>
</template>

<script>
export default {
	data() {
		return {
			avatar: null,
			uploadPath:null,
		};
	},
	onLoad(option){
		this.avatar = option.avatar || '../../static/image/header.jpg';
	},
	created() {
		// 监听从裁剪页发布的事件，获得裁剪结果
		uni.$on('uAvatarCropper', path => {
			this.avatar = path;
			// 可以在此上传到服务端
			uni.uploadFile({
				url: this.uploadPath,
				filePath: path,
				name: 'file',
				complete: res => {
					console.log(res);
				}
			});
		});
	},
	methods: {
		chooseAvatar() {
			this.$u.route({
				url: '/uview-ui/components/u-avatar-cropper/u-avatar-cropper',
				params: {
					// 输出图片宽度，高等于宽，单位px
					destWidth: 300,
					// 裁剪框宽度，高等于宽，单位px
					rectWidth: 200,
					// 输出的图片类型，如果'png'类型发现裁剪的图片太大，改成"jpg"即可
					fileType: 'jpg'
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.wrap {
	padding: 40rpx;
}

.u-avatar-wrap {
	margin-top: 80rpx;
	overflow: hidden;
	margin-bottom: 80rpx;
	text-align: center;
}

.u-avatar-demo {
	width: 150rpx;
	height: 150rpx;
	border-radius: 100rpx;
}
</style>
