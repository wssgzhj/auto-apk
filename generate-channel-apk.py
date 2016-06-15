# coding=utf-8
import sys
import os
import zipfile
import shutil

# 获得本脚本所在目录
d =  os.path.split(os.path.realpath(sys.argv[0]))[0]
# 用于写入到 apk 包中作为 channel 文件
empty_file = d + '/channel'
f = open(empty_file, 'w')
f.close()
# 获取渠道列表
channel_file = d + '/channel.txt'
f = open(channel_file)
lines = f.readlines()
f.close()
# 获得 apk 路径、名称
apk = sys.argv[1]
apk_file_name = os.path.basename(apk)
# 分割文件名与后缀
temp_list = os.path.splitext(apk_file_name)
apk_name = temp_list[0]
apk_extension = temp_list[1]
# 创建输出目录
output_dir = sys.argv[2]
if not os.path.exists(output_dir):
    os.mkdir(output_dir)

# 遍历渠道号并创建对应渠道号的 apk 文件
for line in lines:
    target_channel = line.strip()
    target_apk = output_dir + apk_name + "-" + target_channel + apk_extension
    shutil.copy(apk, target_apk)
    zipped = zipfile.ZipFile(target_apk, 'a', zipfile.ZIP_DEFLATED)
    empty_channel_file = "META-INF/channel_{channel}".format(channel = target_channel)
    zipped.write(empty_file, empty_channel_file)
    zipped.close()
