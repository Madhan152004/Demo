
def call(Map config = [:]) {

    def recipient = config.to ?: ''
    def jobName = env.JOB_NAME ?: 'Unknown Job'
    def buildNumber = env.BUILD_NUMBER ?: 'N/A'
    def buildUrl = env.BUILD_URL ?: ''
    def buildStatus = currentBuild.currentResult ?: 'SUCCESS'

    if (!recipient.trim()) {
        error 'successEmail: recipient email (to) is required'
    }

    emailext(
        to: recipient,
        subject: "✅ SUCCESS | ${jobName} #${buildNumber}",
        mimeType: 'text/html',
        body: """
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body style="margin:0; padding:0; background-color:#f4f6f8;
             font-family:Arial,Helvetica,sans-serif; color:#24292f;">

    <table role="presentation" width="100%" cellpadding="0"
           cellspacing="0" border="0"
           style="background-color:#f4f6f8; padding:24px 8px;">
        <tr>
            <td align="center">

                <table role="presentation" width="600"
                       cellpadding="0" cellspacing="0" border="0"
                       style="width:100%; max-width:600px;
                              background-color:#ffffff;
                              border:1px solid #e1e4e8;
                              border-radius:8px;">

                    <!-- Header -->
                    <tr>
                        <td style="background-color:#16794b;
                                   padding:24px 28px;
                                   border-radius:8px 8px 0 0;">
                            <h1 style="margin:0; color:#ffffff;
                                       font-size:22px; font-weight:600;">
                                &#10003; Build Successful
                            </h1>
                            <p style="margin:8px 0 0; color:#e6f4ec;
                                      font-size:14px;">
                                Jenkins CI/CD Pipeline Notification
                            </p>
                        </td>
                    </tr>

                    <!-- Message -->
                    <tr>
                        <td style="padding:28px;">
                            <p style="margin:0 0 16px; font-size:15px;">
                                Hello Team,
                            </p>

                            <p style="margin:0 0 22px; color:#57606a;
                                      font-size:14px; line-height:1.7;">
                                The Jenkins build has completed successfully.
                                Please review the build information below.
                            </p>

                            <!-- Build Details -->
                            <table role="presentation" width="100%"
                                   cellpadding="0" cellspacing="0" border="0"
                                   style="border-collapse:collapse;
                                          font-size:14px;">

                                <tr>
                                    <td style="padding:12px;
                                               background-color:#f6f8fa;
                                               border:1px solid #d8dee4;
                                               width:38%; font-weight:600;">
                                        Job Name
                                    </td>
                                    <td style="padding:12px;
                                               border:1px solid #d8dee4;
                                               word-break:break-word;">
                                        ${jobName}
                                    </td>
                                </tr>

                                <tr>
                                    <td style="padding:12px;
                                               background-color:#f6f8fa;
                                               border:1px solid #d8dee4;
                                               font-weight:600;">
                                        Build Number
                                    </td>
                                    <td style="padding:12px;
                                               border:1px solid #d8dee4;">
                                        #${buildNumber}
                                    </td>
                                </tr>

                                <tr>
                                    <td style="padding:12px;
                                               background-color:#f6f8fa;
                                               border:1px solid #d8dee4;
                                               font-weight:600;">
                                        Build Status
                                    </td>
                                    <td style="padding:12px;
                                               border:1px solid #d8dee4;">
                                        <span style="display:inline-block;
                                                     padding:5px 10px;
                                                     border-radius:12px;
                                                     background-color:#dafbe1;
                                                     color:#116329;
                                                     font-weight:600;">
                                            ${buildStatus}
                                        </span>
                                    </td>
                                </tr>
                            </table>

                            <!-- Action -->
                            <table role="presentation" cellpadding="0"
                                   cellspacing="0" border="0"
                                   style="margin:24px 0;">
                                <tr>
                                    <td style="background-color:#0969da;
                                               border-radius:6px;">
                                        <a href="${buildUrl}"
                                           style="display:inline-block;
                                                  padding:12px 20px;
                                                  color:#ffffff;
                                                  text-decoration:none;
                                                  font-size:14px;
                                                  font-weight:600;">
                                            View Build Details &#8594;
                                        </a>
                                    </td>
                                </tr>
                            </table>

                            <p style="margin:0; color:#57606a;
                                      font-size:14px; line-height:1.7;">
                                The build is ready for the next stage,
                                subject to the project's release checks.
                            </p>

                            <p style="margin:24px 0 0; font-size:14px;">
                                Regards,<br>
                                <strong>DevOps Team</strong>
                            </p>
                        </td>
                    </tr>

                    <!-- Footer -->
                    <tr>
                        <td style="padding:18px 28px;
                                   background-color:#f6f8fa;
                                   border-top:1px solid #e1e4e8;
                                   border-radius:0 0 8px 8px;">
                            <p style="margin:0; color:#6e7781;
                                      font-size:12px; line-height:1.6;">
                                Automated notification generated by Jenkins.
                                Please do not reply to this email.
                            </p>
                        </td>
                    </tr>

                </table>

            </td>
        </tr>
    </table>

</body>
</html>
"""
    )
}
