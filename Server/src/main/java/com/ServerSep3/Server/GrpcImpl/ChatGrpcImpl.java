package com.ServerSep3.Server.GrpcImpl;

import GrpcClasses.Chat;
import GrpcClasses.ChatsGrpc;
import GrpcClasses.Match;
import com.ServerSep3.Server.Model.ChatModel;
import com.ServerSep3.Server.Model.MatchModel;
import com.ServerSep3.Server.Service.ChatService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class ChatGrpcImpl extends ChatsGrpc.ChatsImplBase {

    @Autowired
    ChatService service;

    @Override
    public void findById(Chat.lookUpById request, StreamObserver<Chat.ChatModel> responseObserver) {
        System.out.println("Finding chat");
        ChatModel model = service.findById(request.getId());
        Chat.ChatModel response = Chat.ChatModel.newBuilder()
            .setId(model.getId())
            .setUserId1(model.getUserId1())
            .setUserId2(model.getUserId2())
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Chat find");
    }



    @Override
    public void deleteChat(Chat.lookUpById request, StreamObserver<Chat.Empty> responseObserver) {
        System.out.println("Deleting chat");
        service.deleteChat((long)request.getId());
        responseObserver.onCompleted();
        System.out.println("chat deleted");
    }

    @Override
    public void findByUserId(Chat.lookUpByUserId request, StreamObserver<Chat.ChatModel> responseObserver) {

    }
}