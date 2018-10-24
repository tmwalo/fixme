package com.gmail.vuyotm.fixme.market.request_process_chain;

public class RequestProcessChain {

    private ProcessChain    processChain1;

    public RequestProcessChain() {
        ProcessChain    processChain2;
        ProcessChain    processChain3;

        processChain1 = new ValidationProcess();
        processChain2 = new ExecutionProcess();
        processChain3 = new ResponseProcess();
        processChain1.setNextProcess(processChain2);
        processChain2.setNextProcess(processChain3);
    }

    public ProcessChain getProcessChain1() {
        return processChain1;
    }

}
