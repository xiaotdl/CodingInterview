state:
    f[i][j]表示第一个sequence的前i个，配上第二个sequence的前j个
function:
    研究第i个和第j个之间的匹配关系（只需研究左，左上，上三个状态）
init:
    f[i][0], f[0][j]
result:
    f[m][n], max/min/cnt/exists