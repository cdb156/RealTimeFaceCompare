package com.hzgc.dubbo.dynamicrepo;

public interface IPCIDService {

    /**
     * 添加IPCID与ftpAddress映射关系
     *
     * @param ipcID 设备ID
     * @return boolean 是否插入成功
     */
    boolean insertIPCID(String ipcID);

    /**
     * 删除IPCID与ftpAddress映射关系
     *
     * @param ipcID 设备ID
     * @return boolean 是否删除成功
     */
    boolean deleteIPCID(String ipcID);

    /**
     * 更新IPCID与ftpAddress映射关系
     *
     * @param ipcID 设备ID
     * @return boolean 是否更新成功
     */
    boolean updtaeIPCID(String ipcID);

    /**
     * 查询IPCID与ftpAddress映射关系
     *
     * @param ipcID 设备ID
     * @return IPCID IPCID与ftpAddress映射关系
     */
    IPCID selectIPCID(String ipcID);

}
