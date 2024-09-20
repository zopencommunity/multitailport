node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/multi_tailport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/multi_tailport.git'), string(name: 'PORT_DESCRIPTION', value: 'MultiTail lets you view one or multiple files like the original tail program. The difference is that it creates multiple windows on your console (with ncurses).' )]
  }
}
