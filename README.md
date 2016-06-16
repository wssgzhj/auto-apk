## auto-apk

*－－节约时间就是延长生命。*

auto-apk 是基于 gradle、shell、python 实现的 android apk 快速多渠道打包工具。

该工具的实现灵感主要来自于 [美团 Android 自动化之旅—生成渠道包](http://tech.meituan.com/mt-apk-packaging.html) ，初心是解决现阶段 android 开发人员打多渠道包时需花费大量时间的问题，彻底实现自动化多渠道打包，以达到解放生产力的目的。

如果使用传统的 gradle 打包方式生成一个 apk 的时间为 1 分钟，生成 30 个渠道的 apk 需要 30 分钟，生成 300 个渠道的 apk 则需要 300 分钟。现在，使用 auto-apk 生成 30 或者 300 个渠道包，只需要花费 2 分钟左右的时间；而且只需一行命令，便可以启动自动化多渠道打包。如此方便、快捷的工具，我们能够有什么理由不去使用它呢？


## 快速使用

1. 下载 auto-apk 项目；
2. 修改 run-packing.sh 文件：
   - *3* 行的 *project_path* 为你的 android 项目路径；
   - *4* 行的 *output_dir* 为多渠道 apk 的输出路径；
   - *5* 行的 *archive_dir* 为多渠道 apk 的存档路径。
3. 修改 channel.txt 中的渠道列表为你想要的；
4. 终端命令行切换到 auto-apk 项目路径下，执行 run-packing.sh 脚本。

```
cd ${auto-apk path}
sh run-packing.sh
```

执行脚本后，会在 *output_dir* 生成多个渠道 apk，并且自动备份该目录下的所有文件到 *archive_dir*。

## 加入我们

目前 auto-apk 自动多渠道打包脚本只实现了 linux 平台的，Windows(.bat)、Mac(.command) 平台的脚本需要更多的人参与来实现，如果你对这个开源项目感兴趣，如果你的初衷也是为了帮助更多的人，那么 auto-apk 项目欢迎你。