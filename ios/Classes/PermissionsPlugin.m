#import "PermissionsPlugin.h"
#import <permissions_plugin/permissions_plugin-Swift.h>

@implementation PermissionsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPermissionsPlugin registerWithRegistrar:registrar];
}
@end
