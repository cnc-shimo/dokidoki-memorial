<?php

namespace App\Model\Table;

use Cake\Database\Expression\IdentifierExpression;
use Cake\ORM\Table;

/**
 * @author shimomo0502
 */
class CouplesTable extends Table
{
    /**
     * @param  array $conditions
     * @return array
     */
    public function getCouples(array $conditions = [])
    {
        $couples = $this->find()->select([
            'id'          => 'Couples.id',
            'man_id'      => 'Couples.man_id',
            'woman_id'    => 'Couples.woman_id',
            'anniversary' => 'Couples.anniversary',
            'created_at'  => 'Couples.created_at',
            'updated_at'  => 'Couples.updated_at',
        ]);

        foreach ($conditions as $key => $value) {
            $couples->where([$key => $value]);
        }

        return json_decode(json_encode($couples->all()), true);
    }
}
