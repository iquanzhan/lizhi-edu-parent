<template>
    <div class="app-container">
        <div class="filter-container">
            <#list table.fields as field>
			<#if !field.keyIdentityFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='deleteStatus'>
			<el-input v-model="listQuery.${field.propertyName}" placeholder="${field.comment}" style="width: 200px;" class="filter-item"
                      @keyup.enter.native="handleFilter"/>
			</#if>
			</#list>
            <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
                搜索
            </el-button>
            <el-button v-waves class="filter-item" type="default" icon="el-icon-refresh" @click="resetFilter">
                重置
            </el-button>
        </div>
        <div style="margin-top: 10px;margin-bottom: 10px;">
            <el-button v-waves class="filter-item" type="primary" icon="el-icon-plus"
                       @click="handleCreate">
                新增
            </el-button>
            <el-button v-waves class="filter-item" :disabled="multipleSelection.length!=1" type="success" icon="el-icon-edit"
                       @click="handleUpdate">
                修改
            </el-button>
            <el-button v-waves class="filter-item" :disabled="multipleSelection.length<=0" type="danger" icon="el-icon-delete"
                       @click="handleBatchDelete">
                删除
            </el-button>
        </div>

        <el-table
                :key="tableKey"
                v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                @selection-change="handleSelectionChange"
                style="width: 100%;"
        >
            <el-table-column
                    type="selection"
                    width="60">
            </el-table-column>
            <el-table-column :label="序号" prop="index" type="index" sortable="custom" align="center" width="80">
            </el-table-column>
			
			<#list table.fields as field>
                <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='deleteStatus'>
            <el-table-column label="${field.comment}" width="100px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.${field.propertyName} }}</span>
                </template>
            </el-table-column>
                </#if>
			</#list>

            <el-table-column :label="操作" align="center" width="230" class-name="small-padding fixed-width">
                <template slot-scope="{row,$index}">
                    <el-button type="text" icon="el-icon-delete" @click="handleDelete(row,$index)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.size"
                    @pagination="getList"/>

        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="dataForm" :rules="rules" :model="temp" label-width="100px" label-position="right"
                     style="padding-right: 30px;">
                <#list table.fields as field>
                    <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='createTime' && field.propertyName!='deleteStatus'>
                <el-form-item label="${field.comment}" prop="${field.propertyName}">
                    <el-input v-model="temp.${field.propertyName}"/>
                </el-form-item>
                    </#if>
                </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    取 消
                </el-button>
                <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                    确 定
                </el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import { fetchList, add${entity}, edit${entity}, delete${entity}, batchDelete${entity} } from '@/api/${entity?uncap_first}'
    import Pagination from '@/components/Pagination'

    export default {
        name: '${entity}List',
        components: { Pagination },
        filters: {},
        data() {
            return {
                tableKey: 0,
                list: [],
                total: 0,
                listLoading: true,
                listQuery: {
                    page: 1,
                    size: 20,
                <#list table.fields as field>
                    <#if !field.keyIdentityFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='deleteStatus'>
                    ${field.propertyName}: '',
                    </#if>
                </#list>
                },
                temp: {
                    id: undefined,
<#list table.fields as field>
    <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime'  && field.propertyName!='createTime' && field.propertyName!='deleteStatus'>
                    ${field.propertyName}: '',
    </#if>
</#list>
                },
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: '编辑',
                    create: '添加'
                },
                rules: {
<#list table.fields as field>
    <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='createTime' && field.propertyName!='deleteStatus'>
                    ${field.propertyName}: [{ required: true, message: '请输入${field.comment}', trigger: 'change' }],
                            </#if>
</#list>
                },
                multipleSelection: []
            }
        },
        created() {
            this.getList()
        },
        methods: {
            handleSelectionChange(val) {
                this.multipleSelection = val
            },
            getList() {
                this.listLoading = true
                fetchList(this.listQuery).then(response => {
                    this.list = response.data.rows
                this.total = response.data.total
                setTimeout(() => {
                    this.listLoading = false
            }, this.$store.state.settings.loadingTime)
            })
            },
            handleFilter() {
                this.listQuery.page = 1
                this.getList()
            },
            //重置
            resetFilter() {
                this.listQuery.page = 1
<#list table.fields as field>
    <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='createTime' && field.propertyName!='deleteStatus'>
                this.listQuery.${field.propertyName} = ''
    </#if>
</#list>
                this.getList()
            },
            resetTemp() {
                this.temp = {
                    id: undefined,
<#list table.fields as field>
    <#if !field.keyFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='updateTime' && field.propertyName!='createTime' && field.propertyName!='deleteStatus'>
                    ${field.propertyName}: '',
    </#if>
</#list>
                }
            },
            handleCreate() {
                this.resetTemp()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
            })
            },
            createData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        add${entity}(this.temp).then((data) => {
                            this.list.unshift(data.data)
                        this.dialogFormVisible = false
                        this.$notify({
                            title: '成功',
                            message: '创建成功',
                            type: 'success',
                            duration: 2000
                        })
                    })
                    }
                })
            },
            handleUpdate(row) {
                this.temp = Object.assign({}, this.multipleSelection[0])
                this.dialogStatus = 'update'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
            })
            },
            updateData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const tempData = Object.assign({}, this.temp)
                        edit${entity}(tempData).then((data) => {
                            const index = this.list.findIndex(v => v.id === this.temp.id)
                        this.list.splice(index, 1, data.data)
                        this.dialogFormVisible = false
                        this.$notify({
                            title: '成功',
                            message: '更新成功',
                            type: 'success',
                            duration: 2000
                        })
                    })
                    }
                })
            },
            handleBatchDelete() {
                this.$confirm('此操作将永久删除${table.comment?substring(0,table.comment?length-1)}数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids = this.multipleSelection.map(item => item.id)
                        batchDelete${entity}(ids).then((data) => {
                            this.$notify({
                                title: '成功',
                                message: '批量删除成功',
                                type: 'success',
                                duration: 2000
                            })
                        this.getList()
                        })
                })
            },
            handleDelete(row, index) {
                this.$confirm('此操作将永久删除该${table.comment?substring(0,table.comment?length-1)}, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delete${entity}(row.id).then((data) => {
                    this.$notify({
                    title: '成功',
                    message: '删除成功',
                    type: 'success',
                    duration: 2000
                })
                this.list.splice(index, 1)
            })
            })
            }
        }
    }
</script>
