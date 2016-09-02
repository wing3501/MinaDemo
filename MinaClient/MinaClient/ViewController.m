//
//  ViewController.m
//  MinaClient
//
//  Created by styf on 16/9/2.
//  Copyright © 2016年 styf. All rights reserved.
//

#import "ViewController.h"
#import "GCDAsyncSocket.h"
@interface ViewController ()<GCDAsyncSocketDelegate>
@property (weak, nonatomic) IBOutlet UIButton *connectBtn;
@property (weak, nonatomic) IBOutlet UIButton *sendBtn;
@property (weak, nonatomic) IBOutlet UITextView *msgTextView;
@property(nonatomic,strong) GCDAsyncSocket *socket;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];   
    
}
- (IBAction)connect:(id)sender {
    [self.socket connectToHost:@"127.0.0.1" onPort:1235 error:nil];
}
- (IBAction)send:(id)sender {
    [self.socket writeData:[@"hello world\r\n" dataUsingEncoding:NSUTF8StringEncoding] withTimeout:-1 tag:0];
}

-(GCDAsyncSocket *)socket {
    if (!_socket) {
        _socket = [[GCDAsyncSocket alloc] initWithDelegate:self delegateQueue:dispatch_get_main_queue()];
        
    }
    return _socket;
}

-(void)socket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    // 1.读取数据
    NSString *dataStr = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    
    [[NSOperationQueue mainQueue] addOperationWithBlock:^{
        self.msgTextView.text = dataStr;
    }];
    
    
    // 2.每次接收完数据，都要再次监听数据
    [sock readDataWithTimeout:-1 tag:0];
}
-(void)socket:(GCDAsyncSocket *)sock didWriteDataWithTag:(long)tag{
    NSLog(@"%s",__func__);
    //发送完数据手动读取，-1不设置超时
    [sock readDataWithTimeout:-1 tag:tag];
}
#pragma mark socket代理
-(void)socket:(GCDAsyncSocket *)sock didConnectToHost:(NSString *)host port:(uint16_t)port{
    NSLog(@"连接主机成功");
    
    [[NSOperationQueue mainQueue] addOperationWithBlock:^{
        self.msgTextView.text = @"连接主机成功";
    }];
    // 监听数据
    [self.socket readDataWithTimeout:-1 tag:0];
}

-(void)socketDidDisconnect:(GCDAsyncSocket *)sock withError:(NSError *)err{
    NSLog(@"连接主机失败 %@",err);
    [[NSOperationQueue mainQueue] addOperationWithBlock:^{
        self.msgTextView.text = @"连接主机失败";
    }];
    
}

@end
