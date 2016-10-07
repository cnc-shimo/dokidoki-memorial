<?php

/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link      http://cakephp.org CakePHP(tm) Project
 * @since     0.2.9
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 */
namespace App\Controller;

use Cake\ORM\TableRegistry;

/**
 * @author shimomo0502
 */
class CouplesController extends AppController
{
    /**
     * @var array
     */
    public $components = ['RequestHandler'];

    /**
     * @return void
     */
    public function index()
    {
        $couples = $this->Couples->find()->select([
            'id'          => 'Couples.id',
            'man_id'      => 'Couples.man_id',
            'woman_id'    => 'Couples.woman_id',
            'anniversary' => 'Couples.anniversary',
            'created_at'  => 'Couples.created_at',
            'updated_at'  => 'Couples.updated_at',
        ])->all();

        $this->set([
            'couples' => $couples,
            '_serialize' => [
                'couples',
            ],
        ]);
    }

    /**
     * @return void
     */
    public function view(string $id)
    {
        $couple = $this->Couples->find()->where([
            'Couples.id' => $id,
        ])->select([
            'id'          => 'Couples.id',
            'man_id'      => 'Couples.man_id',
            'woman_id'    => 'Couples.woman_id',
            'anniversary' => 'Couples.anniversary',
            'created_at'  => 'Couples.created_at',
            'updated_at'  => 'Couples.updated_at',
        ])->first();

        $this->set([
            'id'          => $couple['id'],
            'man_id'      => $couple['man_id'],
            'woman_id'    => $couple['woman_id'],
            'anniversary' => $couple['anniversary'],
            'created_at'  => $couple['created_at'],
            'updated_at'  => $couple['updated_at'],
            '_serialize' => [
                'id',
                'man_id',
                'woman_id',
                'anniversary',
                'created_at',
                'updated_at',
            ],
        ]);
    }
}
