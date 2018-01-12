#gunzip解压gz文件 *是当前文件夹
#grep搜索解压的所有文件 --color关键字标红 'zz'搜索关键字(支持正则)
gunzip -c * | grep --color 'zz'