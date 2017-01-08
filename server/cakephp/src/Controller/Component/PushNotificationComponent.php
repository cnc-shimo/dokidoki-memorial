<?php

namespace App\Controller\Component;

use Cake\Controller\Component;

/**
 * @author shimomo0502
 */
class PushNotificationComponent extends Component
{
    /**
     * @var string
     */
    private $url = 'https://fcm.googleapis.com/fcm/send';

    /**
     * @param string $title
     * @param string $body
     */
    public function send(string $title, string $body)
    {
        $header = [
            'Authorization: key=' . getenv('PUSH_NOTIFICATION_KEY'),
            'Content-Type: application/json',
        ];

        $data = [
            'to' => '/topics/news',
            'priority' => 'high',
            'notification' => [
                'title' => $title,
                'body' => $body,
            ],
        ];

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $this->url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        curl_exec($ch);
        curl_close($ch);
    }
}
